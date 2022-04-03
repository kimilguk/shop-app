package edu.kimilguk.shop.api

import edu.kimilguk.shop.Prefs
import okhttp3.Interceptor
import okhttp3.Response
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

/**
 * 외부 스프링부트API 연결정보를 가로채는 인터셉터 기능 구현
 * 전송헤더중 Authorization 속성에 토큰값을 추가
 */
class ApiTokenInterceptor : Interceptor, AnkoLogger {
    override fun intercept(chain: Interceptor.Chain): Response {
        //TODO("API 연결정보를 가로채는 인터셉터 기능 구현")
        info("API 요청:토큰값 전송")
        val original = chain.request() //상품등록과 같은 기존 요청을 객체를 가져온다.
        //빌더방식으로 새 request 객체생성(아래)
        val request = original.newBuilder().apply {
            Prefs.token?.let { header("Authorization", it) }
            method(original.method(), original.body()) //새 request에 기존 함수와 내용을 추가한다.
        }.build() //빌더형식의 객체 생성은 항상 build()함수로 생성된다.
        info("API 요청:토큰값 전송" + Prefs.refreshtoken.toString())
        return chain.proceed(request) //새 request 기반으로 응답을 반환한다.
    }
}