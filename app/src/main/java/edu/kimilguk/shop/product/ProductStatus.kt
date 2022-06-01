package edu.kimilguk.shop.product

/**
 * object 타입의 싱클톤 객체로 고정값을 저장하는 상수로 지정
 * 스프링부트 API 에서 정의한 상품 판매 상태값 나열값을 정의한다.(아래 enum 클래스)
 * enum class ProductStatus(private val status: String) {
 * SELLABLE("판매중"),
 * SOLD_OUT("품절")
 * }
 */
object ProductStatus {
    val SELLABLE = "SELLABLE"
    val SOLD_OUT = "SOLD_OUT"
}