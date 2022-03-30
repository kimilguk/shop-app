package edu.kimilguk.shop.product

import android.graphics.Color
import android.graphics.Typeface
import android.view.View
import edu.kimilguk.shop.Prefs
import edu.kimilguk.shop.R
import org.jetbrains.anko.*
/**
 * ProductMainActivityUI의 navigationView{} 에 addHeaderView() 사용될 디자인 구현
 */
class ProductMainNaviHeader : AnkoComponent<View> {
    override fun createView(ui: AnkoContext<View>): View {
        return ui.verticalLayout {
            padding = dip(20)
            //네비메뉴 헤더 생성
            imageView(R.drawable.ic_baseline_add_shopping_cart_24)
                .lparams(dip(54), dip(54))
            textView(Prefs.userName) {
                topPadding = dip(8)
                textSize = 20f
                typeface = Typeface.DEFAULT_BOLD //대소문자 주의
            }
            //네비헤더 하단에 1dp 짜리 선 만들기(아래-inline 뷰 만들기)
            view {
                backgroundColor = Color.parseColor("#DDDDDD")
            }.lparams(matchParent, dip(1))
        }
    }
}