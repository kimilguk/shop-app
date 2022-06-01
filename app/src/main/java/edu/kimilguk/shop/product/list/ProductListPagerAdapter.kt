package edu.kimilguk.shop.product.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import edu.kimilguk.shop.product.category.CategoryVO
import edu.kimilguk.shop.product.category.categoryList

/**
 * 뷰페이저에 들어갈 좌우 스와이프 시 데이터를 구현한 뷰페이저 어댑터 클래스
 * FragmentStatePagerAdapter 를 상속한 클래스
 */
class ProductListPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(
    fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT //현재 프레그먼트만 리쥼(재실행)상태가 가능
) {
    // 카테고리 개수 만큼 프레그먼트 객체를 생성한다.
    private val fragments = categoryList.map {
        ProductListFragment.newInstance(it.id, it.name)
    }
    // 오버라이드 구현(implements) 함수 2개 자동 추가(아래)
    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): ProductListFragment {
        return fragments[position]
    }
    // 오버라이드 함수 1개 자동 추가(아래)
    override fun getPageTitle(position: Int): CharSequence? {
        return getItem(position).title
    }
}