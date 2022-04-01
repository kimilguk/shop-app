package edu.kimilguk.shop.product.create

import android.app.Application
import androidx.lifecycle.MutableLiveData
import edu.kimilguk.shop.product.category.categoryList
import net.codephobia.ankomvvm.lifecycle.BaseViewModel

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
    suspend fun createRequest(request: ProductCreateVO) {

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