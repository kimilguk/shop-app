package edu.kimilguk.shop.product.list

import android.app.Application
import android.content.Intent
import androidx.paging.DataSource
import edu.kimilguk.shop.api.response.ProductListItemResponse
import edu.kimilguk.shop.product.detail.ProductDetailActivity
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error
import java.lang.IllegalStateException

/**
 * RecyclerView에 ProductListPagedAdapter 어댑터를 바인딩 +
 * 상품 리스트의 아이템 클릭을 실행하는 클래스(스프링부트의 @서비스와 같은 역할)
 * Application 클래스를 매개변수로 사용한 ankomvvm 의 BaseViewModel 을 상속한 클래스
  */
class ProductListViewModel(app: Application) : BaseViewModel(app),
ProductListPagedAdapter.ProductLiveDataBuilder,//RecyclerView에 ProductListPagedAdapter를 바인딩하는 ProductListViewModel 수정.
ProductListPagedAdapter.OnItemClickListener {

    //RecyclerView에 ProductListPagedAdapter를 바인딩하는 ProductListViewModel 수정 시작
    //var-가변(mutable type), val-불변(immutable type)
    var categoryId: Int = -1 //카테고리 탭 Fragment에서 표시되어야 할 아이템의 카테고리 id 초기값
    val products = buildPagedList() //아이템 배열 리스트 객체 생성
    //ProductListPagedAdapter.ProductLiveDataBuilder 데이터출력 인터페이스 구현
    override fun createDataSource(): DataSource<Long, ProductListItemResponse> {
        if(categoryId == -1) {
            error(
                "카테고리ID가 설정되지 않았습니다.",
                IllegalStateException("categoryId is -1")
            )
        }
        return ProductListItemDataSource(categoryId)
    }
    //ProductListPagedAdapter.OnItemClickListener 상품 클릭이벤트 인터페이스 구현
    override fun onClickProduct(productId: Long?) {
        toast("클릭한 $productId")
        /*startActivity<ProductDetailActivity> {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP //호출된 액티비티가 태스크의 루트에 이미 실행중인 경우 활동이 재실행되지 않는다.
            putExtra(ProductDetailActivity.PRODUCT_ID, productId)
        }*/
    }
    //RecyclerView에 ProductListPagedAdapter를 바인딩하는 ProductListViewModel 수정 끝

    // 상품리스트의 아이템이 클릭되었을 때 호출될 함수(아래)
    /*fun onClickItem(id: Long?) {
        toast("클릭한 $id")
    }*/
}