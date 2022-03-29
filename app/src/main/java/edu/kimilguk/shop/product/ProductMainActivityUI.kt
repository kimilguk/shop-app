package edu.kimilguk.shop.product

import android.graphics.Color
import android.view.Gravity
import android.view.MenuItem.SHOW_AS_ACTION_ALWAYS
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import edu.kimilguk.shop.R
import org.jetbrains.anko.*
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.support.v4.drawerLayout
import org.jetbrains.anko.appcompat.v7.toolbar //수동으로 등록해야 에러가 사라진다.
/**
 * 상품 메인 화면 디자인 구현
 */
class ProductMainActivityUI(
    private val viewModel: ProductMainViewModel //데이터바인딩 객체생성(주입)
) : AnkoComponent<ProductMainActivity>{
    lateinit var navigationView: NavigationView //클래스 멤버변수
    lateinit var toolBar: Toolbar //ProductMainActivity 에서 사용예정
    lateinit var drawerLayout: DrawerLayout //ProductMainActivity 에서 사용예정
    override fun createView(ui: AnkoContext<ProductMainActivity>): View {
        //TODO("드로우어 레이아웃 시작")
        return ui.drawerLayout {
            drawerLayout = this
            verticalLayout {
                toolBar = toolbar { //툴바 추가
                    title = "Shop" //툴바 제목
                    menu.add("Search") //툴바 오른쪽에 메뉴추가,Vector Asset 으로 추가한 아이콘선택
                        .setIcon(R.drawable.ic_baseline_search_24)
                        .setShowAsAction(SHOW_AS_ACTION_ALWAYS) //아이콘 항상 보이기
                }.lparams(matchParent, wrapContent) //Ctrl + 마우스 클릭으로 width, height 크기 지정인 것을 확인
                //툴바 하단에 1dp 짜리 선 만들기(아래-inline 뷰 만들기)
                view {
                    backgroundColor = Color.parseColor("#DDDDDD")
                }.lparams(matchParent, dip(1))
            }.lparams(matchParent, matchParent)//리니어레이아웃을 수직 세로로 배치
            navigationView = navigationView { }.lparams(wrapContent, matchParent) {
                gravity = Gravity.START //네이게이션뷰 위치를 현재 화면의 시작이전부분(왼쪽)으로 배치한다.
            }
        }
    }
}