package edu.kimilguk.shop.api

import edu.kimilguk.shop.Prefs
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
/**
 * 외부 스프링부트API 서비스와 연결시키는 기능
 */
class ApiGenerator : AnkoLogger{
    //Retrofit 모듈을 사용하여 HTTP 통신 객체를 생성(아래)
    //빌더 형식으 함수는 값을 Set 할 때 개발자 명시적으로 사용이 가능하다
    //그래서, 파라미터 매개변수 방식으로 입력하는 것보다 편리하게 사용가능하다.
    fun <T> generate(api: Class<T>): T = Retrofit.Builder()
        .baseUrl(HOST)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient())//기본적으로 OkHttp클라이언트를 사용한다.
        .build()
        .create(api)
    //디버깅 로그를 확인 할 수 있도록 설정함수 추가(아래)
    private fun httpClient(): OkHttpClient = //Convert to expression body 로 변경
        OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingIntercepter())
            addInterceptor(ApiTokenInterceptor()) //스프링부트API사용시 토큰전송 포함
            authenticator(TokenAuthenticator()) //토큰갱신이 필요할 때 인증함수에 토큰인증 클래스를 추가
        }.build()

    private fun httpLoggingIntercepter(): Interceptor = //반환값인 body 를 생성
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    companion object {//static 대신 companion 을 사용, object는 싱글톤 클래스로 실행과 동시에 1개의 객체가 생성된다.
        const val HOST = "http://192.168.1.2:8080"//스프링부트API 서비스 주소
    }
    //토큰갱신요청 API 추가(아래)
    fun <T> generateRefresh(api: Class<T>): T = Retrofit.Builder()
        .baseUrl(HOST)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpRefreshClient())//기본적으로 OkHttp클라이언트를 사용한다.
        .build()
        .create(api)
    //리프레시토큰을 보내는 함수 추가(아래)
    private fun httpRefreshClient(): OkHttpClient = //Convert to expression body 로 변경
        OkHttpClient.Builder().apply {
            addInterceptor(httpLoggingIntercepter())
            addInterceptor(TokenRefreshInterceptor())
        }.build()
}