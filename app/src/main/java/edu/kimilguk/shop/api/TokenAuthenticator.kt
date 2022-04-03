package edu.kimilguk.shop.api

import edu.kimilguk.shop.Prefs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * 스프링부트API 응답코드가 401(권한없음)인 경우
 * SharedPreferences객체에 저장된 refreshToken정보로 토큰갱신 후 새 request 반환
 */
class TokenAuthenticator : Authenticator, AnkoLogger {
    override fun authenticate(route: Route?, response: Response): Request? {
        //TODO("인증정보 처리")
        if(response.code() == 401) {
            info("토근 갱신 필요")
            return runBlocking {
                //대기함수내 일 경우 runBlock으로 코루틴 실행
                val tokenResponse = refreshToken()
                if(tokenResponse.success) {
                    info("토큰 갱신 성공")
                    Prefs.token = tokenResponse.data
                } else {
                    info("토큰 갱신 실패") //에러함수는 catch 문에 넣는것이 좋다.
                    Prefs.token = null
                    Prefs.refreshtoken = null
                }
                Prefs.token?.let { token ->
                    info("토큰 = $token")
                    response.request()
                        .newBuilder()
                        .header("Authorization", token)
                        .build()
                }
            }
        }
        return null
    }

    private suspend fun refreshToken(): ApiResponse<String> {
        return withContext(Dispatchers.IO) {
            try {
                TokenRefreshApi.instance.refreshToken()
            } catch (e: Exception) {
                ApiResponse.error<String>("인증 실패")
            }

        }
    }
}