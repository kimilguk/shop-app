package edu.kimilguk.shop.intro

import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import edu.kimilguk.shop.R
import org.jetbrains.anko.*

/**
 * UI를 xml로 만들지 않고 앙코 모듈을 사용한 코틀린 DSL로 만든다.
 */
class IntroActivityUI : AnkoComponent<IntroActivity> {
    override fun createView(ui: AnkoContext<IntroActivity>): View {
        //TODO("Not yet implemented")
        //코틀린 DSL 언어로 뷰 생성(아래)
        return ui.relativeLayout {
            gravity = Gravity.CENTER
            //문자열 출력(아래)
            textView("쇼핑 앱") {
                textSize = 24f
                textColorResource = R.color.purple_500
                typeface = Typeface.DEFAULT_BOLD
            }
        }
    }
}