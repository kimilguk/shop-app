package edu.kimilguk.shop.product.create

import android.app.Application
import androidx.lifecycle.MutableLiveData
import edu.kimilguk.shop.api.ApiResponse
import edu.kimilguk.shop.api.ShopApi
import edu.kimilguk.shop.product.category.categoryList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.codephobia.ankomvvm.lifecycle.BaseViewModel
import org.jetbrains.anko.error
import retrofit2.Response

/**
 * 상품 등록 로직을 실행하는 클래스(스프링부트의 @서비스와 같은 역할)
 * Application 클래스를 매개변수로 사용한 ankomvvm 의 BaseViewModel 을 상속한 클래스
 * 뷰모델 클래스에 UI에서 사용할 데이터 정의
 */
class ProductCreateViewModel(app: Application) : BaseViewModel(app){
    //UI로 부터 데이터바인딩 시작
    val productName = MutableLiveData("") //UI로 부터 데이터바인딩함수 MutableLiveData
    val description = MutableLiveData("") //자동 바인딩 되려면 UI의 변수명이 여기와 같아야 한다.
    val price = MutableLiveData("")
    val categories = MutableLiveData(categoryList.map {it.name})
    var categoryIdSelected: Int? = categoryList[0].id
    val productNameLimit = 40 //상품명글 숫자 제한변수
    val descriptionLimit = 500 //상품설명글 숫자 제한변수
    //위 2개 변수사용
    val productNameLength = MutableLiveData("0/$productNameLimit")
    val descriptionLength = MutableLiveData("0/$descriptionLimit")

    //상품등록 비지니스 로직함수 시작
    suspend fun createRequest() {
        val request: ProductCreateVO =
            ProductCreateVO(
                productName.value,
                description.value,
                price.value?.toIntOrNull(),
                categoryIdSelected
            )
        val response = when { //UI에서 등록상품 정보를 검증하고 상품등록 API 호출
            request.isNotValidName ->
                ApiResponse.error<Response<Void>>("상품명을 조건에 맞게 입력해 주세요.")
            request.isNotValidDescription ->
                ApiResponse.error<Response<Void>>("상품 설명을 조건에 맞게 입력해 주세요.")
            request.isNotValidPrice ->
                ApiResponse.error<Response<Void>>("상품 가격을 조건에 맞게 입력해 주세요.")
            request.isNotValidCategoryId ->
                ApiResponse.error<Response<Void>>("상품 카테고리를 조건에 맞게 입력해 주세요.")
            else -> withContext(Dispatchers.IO) {
                //네트워크 요청은 코루틴 IO스레드를 사용한 suspend 타입의 함수를 사용
                try {
                    ShopApi.instance.createProduct(request)
                } catch (e: Exception) {
                    error("상품 등록 오류", e) //콘솔에 에러출력
                    ApiResponse.error<Response<Void>>("상품등록시 오류가 발생했습니다.")
                }
            }
        }
        if(response.success) {
            confirm("상품이 등록되었습니다.") { //AlertDialog 창 출력
                finishActivity()
            }
        } else {
            toast(response.message?:"알 수 없는 오류가 발생했습니다.")
            //엘비스 연산자?:물음표 이전 값이 있다면 출력, 없다면 뒤 "알 수 없는~" 출력
        }
    }
    fun checkProductNameLength() { //상품명 글자제한 함수
        productName.value?.let {
            if(it.length > productNameLimit) {
                productName.value = it.take(productNameLimit)
            }
            productNameLength.value = "${productName.value?.length}/$productNameLimit"
        }
    }
    fun checkProductDescriptionLength() { //상품설명 글자제한 함수
        description.value?.let {
            if(it.length > descriptionLimit) {
                description.value = it.take(descriptionLimit)
            }
            descriptionLength.value = "${description.value?.length}/$descriptionLimit"
        }
    }
    fun onCategorySelect(position: Int) { //UI화면에서 선택한 상품카테고리 id값 저장 함수
        categoryIdSelected = categoryList[position].id
    }
}