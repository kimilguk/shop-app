package edu.kimilguk.shop.product

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import edu.kimilguk.shop.R
import edu.kimilguk.shop.product.list.ProductListPagerAdapter
import net.codephobia.ankomvvm.components.BaseActivity
import org.jetbrains.anko.setContentView
import kotlin.reflect.KClass

/**
 * 상품 메인액티비티 클래스 BaseActivity<제네릭제한타입> 상속
 */
class ProductMainActivity : BaseActivity<ProductMainViewModel>() {
    override val viewModelType: KClass<ProductMainViewModel>
        get() = ProductMainViewModel::class //객체생성=::=참조=생성자를 호출한다.
    private val ui by lazy { ProductMainActivityUI(getViewModel()) } //ui객체생성
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.setContentView(this) //drawerLayout has not been initialized 객체초기화 에러발생 방지

        ui.viewpager.adapter = ProductListPagerAdapter(supportFragmentManager);//뷰페이저어댑터 객체생성
        ui.tablayout.setupWithViewPager(ui.viewpager) //탭 레이아웃에 뷰페이저 연결

        /*ProductMainActivityUI(getViewModel()) //위 초기 viewModelType객체를 주입
            .setContentView(this) //UI화면 인플레이트(출력)*/
        //드로우어 토글메뉴 리스너 이벤트 등록(아래)
        val toggle = ActionBarDrawerToggle(
            this,
            ui.drawerLayout,
            ui.toolBar,
            R.string.open_drawer_description,
            R.string.close_drawer_description
        )
        ui.drawerLayout.addDrawerListener(toggle) //액션바 드로어토클 객체를 이벤트리스너로 등록
        toggle.syncState() //네비게이션 드로어 뷰의 상태와 햄버거버튼의 상태 연동
    }
}