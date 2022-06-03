package edu.kimilguk.shop.product.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.codephobia.ankomvvm.components.BaseFragment
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout
import java.lang.IllegalStateException
import kotlin.reflect.KClass

/**
 * 뷰페이저에 들어갈 상품 리스트를 출력해주는 Fragment를 생성
 * ProductListViewModel 클래스를 출력하는 BaseFragment 를 상속한 클래스
 */
class ProductListFragment : BaseFragment<ProductListViewModel>() {
    //오버라이드 구현 뷰모델타입 자동 추가(아래)
    override val viewModelType: KClass<ProductListViewModel>
        get() = ProductListViewModel::class //객체생성=::(참조)=생성자를 호출한다.
        //get() = TODO("Not yet implemented") // 강제로 예외발생
    //ProductListFragment 에서 가지고 있어야할 프로퍼티(멤버변수) 추가(아래)
    val categoryId get() = arguments?.getInt("categoryId")?:throw IllegalStateException("categoryId 없음")
    val title get() = arguments?.getString("title")
        ?:throw IllegalStateException("title 없음")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? { //앙코 코틀린코드로 디자인 출력
        /*return AnkoContext.create(ctx, this).verticalLayout {
            textView(categoryId.toString())
            textView(title)
        }*/
        val viewModel = getViewModel()
        viewModel.categoryId = categoryId //ProductListViewModel에 사용될 카테고리Id 값 입력
        return ProductListUI(viewModel).createView(AnkoContext.create(ctx, this))
    }
    //static 대신 companion 을 사용, object는 싱글톤 클래스로 실행과 동시에 1개의 객체가 생성된다.
    companion object {
        fun newInstance(categoryId: Int, title: String) =
            ProductListFragment().apply {
                // 번들객체를 이용해 프레그먼트에 값을 전달한다.
                arguments = Bundle().also {
                    it.putInt("categoryId", categoryId)
                    it.putString("title", title)
                }
            }
    }
}