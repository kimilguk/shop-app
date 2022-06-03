package edu.kimilguk.shop.product.list

import android.view.Gravity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import net.codephobia.ankomvvm.databinding.bindPagedList
import net.codephobia.ankomvvm.databinding.bindVisibility
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ProductListUI(
    private val viewModel: ProductListViewModel
) : AnkoComponent<ProductListFragment> {
    override fun createView(ui: AnkoContext<ProductListFragment>): View {
        return ui.verticalLayout {
            //상품리스트아이템을 표시하는 리사이클러뷰 사용(아래)
            recyclerView {
                layoutManager = LinearLayoutManager(ui.ctx) //아이템을 리니어로 배치 디자인설정
                adapter = ProductListPagedAdapter(viewModel) //어댑터 객체생성
                lparams(matchParent, matchParent) //리사이클러뷰 가로,세로 크기설정
                bindVisibility(ui.owner, viewModel.products) {
                    it.isNotEmpty() //상품이 있을 때만 true 즉, 보여준다.
                }
                //PagedList를 리사이클러뷰에 바인딩하는 함수(아래)
                bindPagedList(
                    ui.owner,
                    ProductListPagedAdapter(viewModel),
                    viewModel.products
                )
           }
            textView("상품이 없습니다.") {
                gravity = Gravity.CENTER
                bindVisibility(ui.owner, viewModel.products) {
                    it.isEmpty() //상품이 없을 경우 보여줄 디자인설정 true
                }
            }.lparams(wrapContent, matchParent) {
                //가로크기는 내용크기에 맞게, 세로크기는 상위 컨테이너크기에 맞게
                gravity = Gravity.CENTER
            }
        }
    }
}