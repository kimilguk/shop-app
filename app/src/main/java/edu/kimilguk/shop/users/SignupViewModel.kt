package edu.kimilguk.shop.users

import android.app.Application
import androidx.lifecycle.MutableLiveData
import edu.kimilguk.shop.api.ApiResponse
import edu.kimilguk.shop.api.ShopApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error
import java.lang.Exception

/**
 * 회원가입 로직을 실행하는 클래스(스프링부트의 @서비스와 같은 역할)
 */
class SignupViewModel(app: Application): BaseViewModel(app) {
    val email = MutableLiveData("") //anko 데이터바인딩 타입 MutableLiveData
    val name = MutableLiveData("")
    val password = MutableLiveData("")

    suspend fun signup() {
        val request = SignupVO(email.value, password.value, name.value)
        if(isNotValidSignup(request)) {
            return //이메일 유효성 검사 후 true 일때 중지
        }
        try {
            val response = requestSignup(request)//스프링부트API 회원가입 호출
            onSignupResponse(response)//회원가입 이후 결과 처리
        } catch (e: Exception) {
            error("회원가입 에러", e)//콘솔에 에러메시지 출력
            toast("회원가입에 실패 했습니다.")
        }
    }

    private fun onSignupResponse(response: ApiResponse<Void>) {
        //TODO("Not yet implemented")//강제로 예외 발생
        if(response.success) {
            toast("회원가입이 되었습니다. 로그인 후 이용해 주세요")
            finishActivity() //회원가입 후 현재 화면 제거
        } else {
            //?: 엘비스연산자 사용해서 null 일때 출력값 생성
            toast(response.message ?: "회원가입에서 알 수 없는 오류가 발생했습니다.")
        }
    }

    /*private suspend fun requestSignup(request: SignupVO): Any {
        //코루틴 영역(스레드 비동기 처리)에 withContext사용(아래)
        return withContext(Dispatchers.IO) {
            ShopApi.instance.signup(request)
        }
    } //우리 코딩에서는 return 키워드를 사용하지 않고 = 로 대체한다 */
    private suspend fun requestSignup(request: SignupVO) =
        //코루틴 영역(스레드 비동기 처리)에 withContext사용(아래)
        withContext(Dispatchers.IO) {
            ShopApi.instance.signup(request)
        }

    private fun isNotValidSignup(request: SignupVO) =
        when {
            request.isNotValidEmail() -> {
                toast("이메일 형식이 정확하지 않습니다.")
                true
            }
            request.isNotValidPassword() -> {
                toast("암호는 8자 이상 20자 이하로 입력해 주세요")
                true
            }
            request.isNotValidName() -> {
                toast("이름은 2자 이상 20자 이하로 입력해 주세요")
                true
            }
            else -> false
        }
    }