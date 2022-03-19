package edu.kimilguk.shop.auth

import android.app.Application
import androidx.lifecycle.MutableLiveData
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

/**
 * 회원로그인 로직을 실행하는 클래스(스프링부트의 @서비스와 같은 역할)
 */
class SigninViewModel(app: Application) : BaseViewModel(app) {
    val email = MutableLiveData("")//anko 데이터바인딩 타입 MutableLiveData
    val password = MutableLiveData("")
}