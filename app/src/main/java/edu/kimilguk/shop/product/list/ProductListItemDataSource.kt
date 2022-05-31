package edu.kimilguk.shop.product.list

import androidx.paging.PageKeyedDataSource
import edu.kimilguk.shop.App
import edu.kimilguk.shop.api.ApiResponse
import edu.kimilguk.shop.api.ShopApi
import edu.kimilguk.shop.api.response.ProductListItemResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.jetbrains.anko.toast

/**
 * PageKeyedDataSource(초기데이터, next데이터, prev데이터 오버라이드기능포함)
 * 위 클래스를 상속한 ProductListItemDataSource 클래스
 */
class ProductListItemDataSource(
    private val categoryId: Int?
) : PageKeyedDataSource<Long, ProductListItemResponse>(){
    //아래 상품데이터를 로드하는 콜백 함수는 객체화 될때 자동 실행된다.
    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, ProductListItemResponse>,
    ) {
        val response = getProducts(Long.MAX_VALUE, NEXT)
        if(response.success) { //객체?.let : 객체가 null 인지 체크해서 값이 있다면 {구현}
            response.data?.let {
                if(it.isNotEmpty()) {
                    callback.onResult(it, it.first().id, it.last().id)
                    // it은 List<ProductListItemResponse> 배열데이터 이다.
                    // id는 이전-다음 데이터를 가져오는데 사용
                }
            }
        } else {
            GlobalScope.launch(Dispatchers.Main) {
                showErrorMessage(response)
            }
        }
    }

    private fun showErrorMessage(response: ApiResponse<List<ProductListItemResponse>>) {
        App.instance.toast( //아래 ?: 엘비스연산자사용, 메시지가 null 이라면 알 수 없는~ 이 출력됨.
            response.message ?: "알 수 없는 오류가 발생했습니다."
        )
    }

    private fun getProducts(id: Long, direction: String) = runBlocking {
       try {
           ShopApi.instance.getProducts(id, categoryId, direction)
       } catch (e:Exception) {
           ApiResponse.error<List<ProductListItemResponse>>(
               "알 수 없는 오류가 발생했습니다."
           )
       }
    }

    companion object {//static 대신 companion 을 사용, object는 싱글톤 클래스로 실행과 동시에 1개의 객체가 생성된다.
        private const val NEXT = "next"
        private const val PREV = "prev"
    }
    //현재 상품배열 기준 prev 데이터 가져오기
    override fun loadBefore(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, ProductListItemResponse>,
    ) {
        val response = getProducts(params.key, PREV)
        if (response.success) {
            response.data?.let {
                if (it.isNotEmpty()) {
                    callback.onResult(it, it.first().id)
                    // it은 List<ProductListItemResponse> 배열데이터 이다.
                    // id는 이전 데이터를 가져오는데 사용
                }
            }
        } else {
            GlobalScope.launch(Dispatchers.Main) {
                showErrorMessage(response)
            }
        }
    }
    //현재 상품배열 기준 next 데이터 가져오기
    override fun loadAfter(
        params: LoadParams<Long>,
        callback: LoadCallback<Long, ProductListItemResponse>,
    ) {
        val response = getProducts(params.key, NEXT)
        if(response.success) {
            response.data?.let {
                if(it.isNotEmpty()) {
                    callback.onResult(it, it.last().id)
                    // it은 List<ProductListItemResponse> 배열데이터 이다.
                    // id는 다음 데이터를 가져오는데 사용
                }
            }
        } else {
            GlobalScope.launch(Dispatchers.Main) {
                showErrorMessage(response)
            }
        }
    }
}