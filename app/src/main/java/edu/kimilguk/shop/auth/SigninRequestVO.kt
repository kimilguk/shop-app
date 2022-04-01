package edu.kimilguk.shop.auth

import android.util.Patterns
/**
 * 스프링부트로 회원 인증 데이터 요청 시 임시 저장할 데이터 SigninRequestVO 클래스
 * 스프링부트 API 와 입출력시 데이터를 임시 저장한다.
 */
data class SigninRequestVO(
    val email: String?, //회원 email
    val password: String?//회원 암호
) {
    fun isNotValidEmail() = //이메일 유효성검사
        email.isNullOrBlank()
                || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    fun isNotValidPassword() = //암호 유효성검사
        password.isNullOrBlank() || password.length !in 8..20
}
