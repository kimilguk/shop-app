package edu.kimilguk.shop.users

import android.util.Patterns

/**
 * 클래스간 데이터 전송 임시 저장 VO(Value Object)클래스
 */
class SignupVO(
    val email: String?,//?는 Null 허용을 표시한다. 코틀린은 기본이 Null 허용하지 않음.
    val password: String?,
    val name: String?
) {
    fun isNotValidEmail() = //이메일 유효성 검사 true || false
        email.isNullOrBlank()
                || !Patterns.EMAIL_ADDRESS.matcher(email).matches()
    fun isNotValidPassword() = //암호 유효성 검사 true || false
        password.isNullOrBlank() || password.length !in 8..20
    fun isNotValidName() = //회원명 유효성 검사 true || false
        name.isNullOrBlank() || name.length !in 2..20
}