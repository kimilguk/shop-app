package edu.kimilguk.shop.auth

import android.app.Application
import androidx.lifecycle.MutableLiveData
import edu.kimilguk.shop.Prefs
import edu.kimilguk.shop.api.ApiResponse
import edu.kimilguk.shop.api.ShopApi
import edu.kimilguk.shop.product.ProductMainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error

/**
 * 회원로그인 로직을 실행하는 클래스(스프링부트의 @서비스와 같은 역할)
 * Application 클래스를 매개변수로 사용한 ankomvvm 의 BaseViewModel 을 상속한 클래스
 * 뷰모델 클래스에 UI에서 사용할 데이터 정의
 */
class SigninViewModel(app: Application) : BaseViewModel(app) {
    val email = MutableLiveData("")//anko 데이터바인딩 타입 MutableLiveData
    val password = MutableLiveData("")
    //비동기 통신에 사용되는 suspend 함수 사용
    suspend fun signin() {
        val request = SigninRequestVO(email.value, password.value)
        if(isNotValidSignin(request)) return
        try{
            val response = requestSignin(request)//로그인 처리
            onSigninResponse(response)//로그인 성공 후 처리
        }catch(e:Exception) {
            error("로그인 에러", e)
            toast("알수 없는 오류가 발생했습니다.")
        }

    }

    private fun onSigninResponse(response: ApiResponse<SigninResponseVO>) {
        //TODO("로그인 성공 후 처리")
        if(response.success && response.data != null) {
            //response.data 토큰값이 있을때 저장로직(아래)
            Prefs.token = response.data.token
            Prefs.refreshtoken = response.data.refreshToken
            Prefs.userName = response.data.userName
            Prefs.userId = response.data.userId
            toast("로그인 되었습니다.")
            startActivityAndFinish<ProductMainActivity>() //로그인 후 현재화면 종료 및 상폼 메인화면으로 입장
        } else {
            toast(response.message?:"알수 없는 오류가 발생했습니다.")
        }
    }

    private suspend fun requestSignin(request: SigninRequestVO) =
        //TODO("코루틴 로그인 스래드 시작")
        withContext(Dispatchers.IO) {
            ShopApi.instance.signin(request)//로그인 비동기 통신 실행
        }

    private fun isNotValidSignin(request: SigninRequestVO)=
        when {
            request.isNotValidEmail() -> {
                toast("이메일 형식이 올바르지 않습니다.")
                true
            }
            request.isNotValidPassword() -> {
                toast("암호는 8자 이상 20자 이하로 입력해 주세요")
                true
            }
            else -> false
        }
}