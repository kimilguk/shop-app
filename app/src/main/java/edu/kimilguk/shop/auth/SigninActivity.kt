package edu.kimilguk.shop.auth

import android.os.Bundle
import edu.kimilguk.shop.users.SignupActivityUI
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView
import kotlin.reflect.KClass

/**
 * 로그인 액티비티 클래스 BaseActivity<제네릭제한타입> 상속
 */
class SigninActivity : BaseActivity<SigninViewModel>() {
    /*override val viewModelType: KClass<SigninViewModel>
        get() = SigninViewModel::class//객체생성=::=참조=생성자를 호출한다.//TODO("Not yet implemented")*/
    override val viewModelType = SigninViewModel::class//객체생성=::=참조=생성자를 호출한다.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SigninActivityUI(getViewModel()) //위 초기 viewModelType객체를 주입
            .setContentView(this)//this에 SigninActivityUI뷰를 인플레이트(출력) 시킨다.
    }
}