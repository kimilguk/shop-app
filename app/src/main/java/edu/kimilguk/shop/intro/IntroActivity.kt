package edu.kimilguk.shop.intro

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.kimilguk.shop.Prefs
import edu.kimilguk.shop.api.ShopApi
import edu.kimilguk.shop.auth.SigninActivity
import edu.kimilguk.shop.product.ProductMainActivity
import edu.kimilguk.shop.users.SignupActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity
import java.lang.Exception

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val ui = IntroActivityUI() //new 키워드 없이 객체 생성
        ui.setContentView(this)
        //스프링부트API의 hello() 호출하기 아래 runBlocking{구현내용}은
        //어떠한 코드 블록 내 작업이 완료 되기를 기다리는 가장 간단한 방법
        runBlocking {
            try{
                val response = ShopApi.instance.hello()
                response.data?.let { Log.d("인트로액티비티", it) }
            } catch (e: Exception) {
                Log.d("인트로액티비티", "/api/hello 호출오류", e)
            }
        }
        //코루틴영역에서 스레드 실행:인트로 액티비티 이 후 회원가입 액티비티 띄우기
        GlobalScope.launch {
            delay(1000)//1초 대기
            if(Prefs.token.isNullOrEmpty()) {//SharedPreferences에 등록된 토큰이 없다면,
                startActivity<SigninActivity>() //로그인 화면으로 이동
            } else {
                startActivity<ProductMainActivity>() //상폼 메인화면으로 이동
            }
            finish()//현재 인트로 액티비티 종료(뒤로가기 해도 인트로가 보이지 않음)
        }
    }
}