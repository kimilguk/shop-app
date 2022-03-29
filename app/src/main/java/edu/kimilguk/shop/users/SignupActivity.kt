package edu.kimilguk.shop.users

import android.os.Bundle
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView
import kotlin.reflect.KClass

/**
 * 회원가입 액티비티 클래스 BaseActivity<제네릭제한타입> 상속
 */
class SignupActivity : BaseActivity<SignupViewModel>() {
    override val viewModelType: KClass<SignupViewModel>
        get() = SignupViewModel::class //객체생성=::=참조=생성자를 호출한다.
        //get() = TODO("Not yet implemented")//강제로 예외발생됨
    //override val viewModelType = SignupViewModel::class
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SignupActivityUI(getViewModel()) //위 초기 viewModelType객체를 주입
            .setContentView(this)//this에 SignupActivityUI뷰를 인플레이트(출력) 시킨다.
    }
}