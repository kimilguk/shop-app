package edu.kimilguk.shop.product.create

import android.os.Bundle
import android.view.MenuItem
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView
import kotlin.reflect.KClass

/**
 * 상품 등록액티비티 클래스 BaseActivity<제네릭제한타입> 상속
 */
class ProductCreateActivity : BaseActivity<ProductCreateViewModel>() {
    override val viewModelType: KClass<ProductCreateViewModel>
        get() = ProductCreateViewModel::class//TODO("뷰모델 객체생성")
    //화면생성: 액션바는 UI에서 만들지 않고 코딩으로 생성
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) //부모클래스 onCreate함수 초기화
        supportActionBar?.setDisplayHomeAsUpEnabled(true)//상단 액션바에 홈,뒤로가기 바튼 보이기
        supportActionBar?.title = "상품 등록"//상단 액션바에 제목 추가
        //디자인 UI와 매칭(아래)
        ProductCreateActivityUI(getViewModel())
            .setContentView(this)
    }
    //액션바 버튼 선택 이벤트 처리 콜백함수(자동실행)
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //MenuItem(Null허용하는)이 있다면 { when~else구문 = switch~case구문
        item?.let {
            when(item.itemId) {
                android.R.id.home -> onBackPressed()
                else -> {}
            }
        }
        return true
    }
}