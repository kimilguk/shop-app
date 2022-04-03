package edu.kimilguk.shop.api

import android.content.Intent
import edu.kimilguk.shop.App
import edu.kimilguk.shop.Prefs
import edu.kimilguk.shop.auth.SigninActivity
import okhttp3.Interceptor
import okhttp3.Response
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.intentFor

/**
 * 외부 스프링부트API 연결정보를 가로채는 인터셉터 기능 구현
 * 전송헤더중 Authorization 속성에 재생토큰값을 추가
 */
class TokenRefreshInterceptor : Interceptor, AnkoLogger {
    override fun intercept(chain: Interceptor.Chain): Response {
        //TODO("API 연결정보를 가로채는 인터셉터 기능 구현")
        info("API 요청:토큰 갱신값 전송")
        val original = chain.request() //상품등록과 같은 기존 요청을 객체를 가져온다.
        //빌더방식으로 새 request 객체생성(아래)
        val request = original.newBuilder().apply {
            Prefs.refreshtoken?.let { header("Authorization", it) }
            method(original.method(), original.body()) //새 request에 기존 함수와 내용을 추가한다.
        }.build() //빌더형식의 객체 생성은 항상 build()함수로 생성된다.
        val response = chain.proceed(request)//새 request 기반으로 응답객채 생성.
        info("API 요청:토큰 갱신값 전송" + Prefs.refreshtoken.toString())
        //응답 객체를 받아 재생토큰에 대한 권한이 없다면 로그인 으로 이동(아래)
        if(response.code() == 401) {
            App.instance.run {
                val intent = intentFor<SigninActivity>().apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                } //TASk 태스크는 액티비티 순서를 관리하는 클래스이다. 위에서는 현재 실행된 액티비티를 태스크에서 지우고 새로 태스크 순서를 만든다. 위 2개를 한쌍으로 사용한다. 뒤로 가기가 없어진다.
                startActivity(intent)
            }
        }
        return response
    }
}