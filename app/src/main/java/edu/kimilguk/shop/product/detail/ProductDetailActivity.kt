package edu.kimilguk.shop.product.detail

import net.codephobia.ankomvvm.components.BaseActivity
import kotlin.reflect.KClass

class ProductDetailActivity : BaseActivity<ProductDetailViewModel>() {
    companion object {//static 대신 companion 을 사용, object는 싱글톤 클래스로 실행과 동시에 1개의 객체가 생성된다.
        val PRODUCT_ID = "productId"
    }

    override val viewModelType: KClass<ProductDetailViewModel>
        get() = ProductDetailViewModel::class
}
