package edu.kimilguk.shop.auth

import android.graphics.Color
import android.graphics.Typeface
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import edu.kimilguk.shop.R
import edu.kimilguk.shop.users.SignupActivity
import net.codephobia.ankomvvm.databinding.bindString
import org.jetbrains.anko.*
import org.jetbrains.anko.design.textInputEditText
import org.jetbrains.anko.design.textInputLayout
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * 회원로그인 화면 디자인 구현
 */
class SigninActivityUI(
    private val viewModel: SigninViewModel //데이터바인딩처리 객체생성(주입)
) : AnkoComponent<SigninActivity> {
    //override fun createView(ui: AnkoContext<SigninActivity>): View {
    override fun createView(ui: AnkoContext<SigninActivity>) =
        //TODO("리니어 레이아웃 시작")
        ui.linearLayout {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_VERTICAL
            padding = dip(20)

            textView("Shop") {
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                textSize = 24f
                typeface = Typeface.DEFAULT_BOLD
                textColorResource = R.color.purple_500
            }.lparams(width = matchParent) {
                bottomMargin = dip(50)
            }

            textInputLayout {
                textInputEditText {
                    hint = "이메일을 입력하세요"
                    setSingleLine()
                    bindString(ui.owner, viewModel.email)
                }
            }.lparams(width = matchParent) {
                bottomMargin = dip(20)
            }

            textInputLayout {
                textInputEditText {
                    hint = "암호를 입력하세요"
                    setSingleLine()
                    inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                    bindString(ui.owner, viewModel.password)
                }
            }.lparams(width = matchParent) {
                bottomMargin = dip(20)
            }

            button("로그인") {
                onClick { viewModel.signin() }
            }.lparams(width = matchParent)

            button("회원가입") {
                backgroundColor = Color.TRANSPARENT
                textColorResource = R.color.purple_500
                onClick { ui.startActivity<SignupActivity>() }
            }
        }//리니어레이아웃 끝
}