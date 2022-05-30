package edu.kimilguk.shop.product.list

import android.app.Application
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

/**
 * 상품 리스트의 아이템 클릭을 실행하는 클래스(스프링부트의 @서비스와 같은 역할)
 * Application 클래스를 매개변수로 사용한 ankomvvm 의 BaseViewModel 을 상속한 클래스
 * 뷰모델 클래스에 UI에서 사용할 데이터 정의
 */
class ProductListViewModel(app: Application) : BaseViewModel(app){
    // 상품리스트의 아이템이 클릭되었을 때 호출될 함수(아래)
    fun onClickItem(id: Long?) {
        toast("클릭한 $id")
    }
}