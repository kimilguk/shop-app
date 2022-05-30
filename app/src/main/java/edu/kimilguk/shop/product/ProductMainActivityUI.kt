package edu.kimilguk.shop.product

import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.Menu.NONE
import android.view.MenuItem
import android.view.MenuItem.SHOW_AS_ACTION_ALWAYS
import android.view.View
import android.view.View.generateViewId
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.GRAVITY_FILL
import com.google.android.material.tabs.TabLayout.MODE_SCROLLABLE
import edu.kimilguk.shop.Prefs
import edu.kimilguk.shop.R
import edu.kimilguk.shop.auth.SigninActivity
import org.jetbrains.anko.*
import org.jetbrains.anko.design.navigationView
import org.jetbrains.anko.support.v4.drawerLayout
import org.jetbrains.anko.appcompat.v7.toolbar //수동으로 등록해야 에러가 사라진다.
import org.jetbrains.anko.design.floatingActionButton
import org.jetbrains.anko.design.themedTabLayout
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.viewPager

/**
 * 상품 메인 화면 디자인 구현
 * AnkoComponent<해당액티비티> 상속
 */
class ProductMainActivityUI(
    private val viewModel: ProductMainViewModel //데이터바인딩처리 객체생성(주입)
) : AnkoComponent<ProductMainActivity>, NavigationView.OnNavigationItemSelectedListener {
    lateinit var navigationView: NavigationView //클래스 멤버변수:네비게이션 뷰 닫을 때 사용
    lateinit var toolBar: Toolbar //ProductMainActivity 에서 사용예정
    lateinit var drawerLayout: DrawerLayout //ProductMainActivity 에서 사용예정
    lateinit var tablayout: TabLayout // 카테고리 탭은 ProductMainActivity 에서 사용예정
    lateinit var viewpager: ViewPager // 상품리스트 뷰페이저는 ProductMainActivity 에서 사용예정
    override fun createView(ui: AnkoContext<ProductMainActivity>): View {
        //TODO("드로우어 레이아웃 시작")
        return ui.drawerLayout {
            drawerLayout = this
            //상품 등록 버튼 출력 때문에 frameLayout 으로 묶어줌
            frameLayout {
                //리니어 레이아웃 세로방향 툴바 시작(아래)
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

                    //카테고리 TabLayout을 생성하는 함수와 빌더(아래)
                    tablayout = themedTabLayout(
                        net.codephobia.ankomvvm.R.style.Widget_MaterialComponents_TabLayout
                    ) {
                        bottomPadding = dip(1)
                        tabMode = MODE_SCROLLABLE //카테고리 탭 크기를 유지하면서 스크롤 할 수 있게 처리
                        tabGravity = GRAVITY_FILL //탭 영역을 상위 컨테이너에 맞게 가득 채운다.
                        lparams(matchParent, wrapContent)
                    }
                    //카테고리 탭 하단에 1dp 짜리 선 만들기(아래-inline 뷰 만들기)
                    view {
                        backgroundColor = Color.parseColor("#DDDDDD")
                    }.lparams(matchParent, dip(1))
                    //뷰페이저 생성(아래)
                    viewpager = viewPager {
                        id = generateViewId() //뷰페이저와 탭레이아웃 연동시 view클래스의 id값 필수
                    }.lparams(matchParent, matchParent)
                }.lparams(matchParent, matchParent)//리니어레이아웃을 수직 세로로 배치

                //상품 등록 버튼 출력(아래)
                floatingActionButton {
                    imageResource = R.drawable.ic_baseline_add_circle_outline_24
                    onClick { viewModel.openCreateActivity() }
                }.lparams {
                    bottomMargin = dip(20) //아래쪽 마진
                    marginEnd = dip(20) //오른쪽 마진
                    gravity = Gravity.END or Gravity.BOTTOM
                }
            }//frameLayout
            //네비게이션은 숨었다가 스와이프로 보여짐
            navigationView = navigationView {
                //네비 헤더 외부 파일에서 가져오기(아래)
                ProductMainNaviHeader()
                    .createView(AnkoContext.create(context, this))
                    .run { addHeaderView(this) } //(::addHeaderView) 이렇게 해도 된다.
                //메뉴추가
                menu.apply {
                    add(NONE, MENU_ID_QA, NONE, "내 문의").apply {
                        setIcon(R.drawable.ic_baseline_post_add_24)
                    }
                    add(NONE, MENU_ID_LOGOUT, NONE, "로그아웃").apply {
                        setIcon(R.drawable.ic_baseline_login_24)
                    }
                }
                //메뉴 선택 리스너로 이벤트 처리(아래 추가 이후 implements 함수 추가)
                setNavigationItemSelectedListener(this@ProductMainActivityUI)
            }.lparams(wrapContent, matchParent) {
                gravity = Gravity.START //네이게이션뷰 위치를 현재 화면의 시작이전부분(왼쪽)으로 배치한다.
            }

        }//drawerLayout
    }
    companion object {//static 대신 companion 을 사용, object는 싱글톤 클래스로 실행과 동시에 1개의 객체가 생성된다.
        private const val MENU_ID_QA = 1
        private const val MENU_ID_LOGOUT = 2
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        //TODO("메뉴 선택 이벤트추가)
        when(item.itemId) { //switch(조건) case(결정값) 구문과 같음
            MENU_ID_QA -> { viewModel.toast("내 문의를 선택")}
            MENU_ID_LOGOUT -> {
                Prefs.token = null
                Prefs.refreshtoken = null
                viewModel.toast("로그아웃 되었습니다.")
                viewModel.startActivityAndFinish<SigninActivity>()
            }
        }
        drawerLayout.closeDrawer(navigationView) //네비메뉴 닫기
        return true
    }

}