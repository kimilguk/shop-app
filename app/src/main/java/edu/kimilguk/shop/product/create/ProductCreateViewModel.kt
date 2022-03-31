package edu.kimilguk.shop.product.create

import android.app.Application
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

/**
 * 상품 등록 로직을 실행하는 클래스(스프링부트의 @서비스와 같은 역할)
 * Application 클래스를 매개변수로 사용한 ankomvvm 의 BaseViewModel 을 상속한 클래스
 */
class ProductCreateViewModel(app: Application) : BaseViewModel(app){
}