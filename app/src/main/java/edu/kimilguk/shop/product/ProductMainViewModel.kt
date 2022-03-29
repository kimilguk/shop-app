package edu.kimilguk.shop.product

import android.app.Application
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

/**
 * 상품 진입 로직을 실행하는 클래스(스프링부트의 @서비스와 같은 역할)
 */
class ProductMainViewModel(app: Application): BaseViewModel(app) {
    fun openCreateActivity() {
        toast("상품 등록 UI가 준비되면 해당액티비티를 열도록 추가")
    }
}