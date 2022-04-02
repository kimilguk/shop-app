package edu.kimilguk.shop.product

import android.app.Application
import android.content.Intent
import edu.kimilguk.shop.product.create.ProductCreateActivity
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

/**
 * 상품 진입 로직을 실행하는 클래스(스프링부트의 @서비스와 같은 역할)
 * Application 클래스를 매개변수로 사용한 ankomvvm 의 BaseViewModel 을 상속한 클래스
 * 뷰모델 클래스에 UI에서 사용할 데이터 정의
 */
class ProductMainViewModel(app: Application): BaseViewModel(app) {
    fun openCreateActivity() {
        toast("상품 등록 UI가 준비되면 해당액티비티를 열도록 추가")
        startActivity<ProductCreateActivity> {
            flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            //액티비티를 호출할 때 호출된 액티비티의 인스턴스가
            // 현재 태스크에 이미 실행중인 경우 활동이 재실행되지 않는다
            // ( = 실수로 같은 상품을 2번 등록하는 것을 방지한다.)
            /* 참고로
            FLAG_ACTIVITY_CLEAR_TOP: 이미 존재하던 액티비티는 재생성 되며 시작된다.
            */
        }
    }
}