package edu.kimilguk.shop.api

import retrofit2.http.POST
import retrofit2.http.Query

/**
 * 스프링부트API 리프레시 토큰 기능에 접근하는 인터페이스
 */
interface TokenRefreshApi {
    @POST("/api/refresh_token")
    suspend fun refreshToken(//함수앞에 suspend 키워드를 추가해서 비동기 통신용 서스펜딩(백그라운드 대기) 함수임을 명시한다
        @Query("grant_type") grantType: String = "refresh_token" //스프링부트API SigninApiController.kt 컨트롤러 클래스 참조
    ): ApiResponse<String>
    //static 대신 companion 을 사용, object는 싱글톤 클래스로 실행과 동시에 1개의 객체가 생성된다.
    companion object {
        val instance = ApiGenerator() //외부 스프링부트API 서비스와 연결객체
            .generateRefresh(TokenRefreshApi::class.java)
    }
}