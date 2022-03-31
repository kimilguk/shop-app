package edu.kimilguk.shop.product.create

import android.view.View
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.scrollView
import org.jetbrains.anko.verticalLayout

/**
 * 상품 등록 화면 디자인 구현
 * AnkoComponent<해당액티비티> 상속
 */
class ProductCreateActivityUI(
    private val viewModel: ProductCreateViewModel //데이터바인딩처리 객체생성(주입)
) : AnkoComponent<ProductCreateActivity> {
    override fun createView(ui: AnkoContext<ProductCreateActivity>): View {
        //TODO("레이아웃생성: 세로스크롤 뷰로 감싸주고, 리니어레이아웃 배치")
        return ui.scrollView {
            verticalLayout {

            }
        }
    }
}