package edu.kimilguk.shop.auth
/**
 * 스프링부트에서 회원 인증토큰 응답 시 임시 저장할 데이터 SigninResponseVO 클래스
 * 스프링부트 API 와 입출력시 데이터를 임시 저장한다.
 */
data class SigninResponseVO(
    val token: String,
    val refreshToken: String,
    val userName: String,
    val userId: Long
)
