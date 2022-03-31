package edu.kimilguk.shop.users

import android.graphics.Typeface
import android.renderscript.ScriptGroup
import android.text.InputType
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import edu.kimilguk.shop.R
import net.codephobia.ankomvvm.databinding.bindString
import org.jetbrains.anko.*
import org.jetbrains.anko.design.textInputEditText
import org.jetbrains.anko.design.textInputLayout
import org.jetbrains.anko.sdk27.coroutines.onClick

/**
 * 회원가입 화면 디자인 구현
 */
class SignupActivityUI(
    private val viewModel: SignupViewModel //데이터바인딩처리 객체생성(주입)
) : AnkoComponent<SignupActivity> {
    override fun createView(ui: AnkoContext<SignupActivity>) =
        //TODO("리니어 레이아웃 시작")
        ui.linearLayout {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER_VERTICAL
            padding = dip(20)

            textView("회원가입") {
                textAlignment = TextView.TEXT_ALIGNMENT_CENTER
                textSize = 20f
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
                    hint = "이름을 입력하세요"
                    setSingleLine()
                    bindString(ui.owner, viewModel.name)
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

            button("회원가입") {
                onClick { viewModel.signup() }
            }.lparams(width = matchParent)
        }//리니어레이아웃 끝
    }
