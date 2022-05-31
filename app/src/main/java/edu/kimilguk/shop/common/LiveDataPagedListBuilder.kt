package edu.kimilguk.shop.common

import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList

/**
 * 페이징 데이터 빌더 인터페이스: 최종데이터인 LiveData<PagedList>를 만드는 인터페이스
 * common 패키지에 공통소스로 분리한다.
 * androidx에 내장된 LivePagedListBuilder 클래스 사용
 */
interface LiveDataPagedListBuilder<K, T> {
    fun createDataSource(): DataSource<K, T> //데이터소스 객체 생성에 필요한 클래스 반환
    private fun factory() = object : DataSource.Factory<K, T>() { //데이터소스 객체 생성
        override fun create(): DataSource<K, T> {
            return createDataSource()
        }
    }
    //페이징 기본값 설정(아래)
    private fun config() = PagedList.Config.Builder()
        .setPageSize(10)
        .setEnablePlaceholders(false)
        .build()
    //androidx에 내장된 LivePagedListBuilder 클래스 객체를 사용해서 페이징 사용설정(아래)
    fun buildPagedList() = LivePagedListBuilder(factory(), config()).build()
}