package edu.kimilguk.shop.product.create
/**
 * 클래스간 데이터 전송 임시 저장 VO(Value Object)클래스
 * 스프링부트 API 와 입출력시 데이터를 임시 저장한다.
 */
data class ProductCreateVO(
    val name: String?,
    val description: String?,
    val price: Int?,
    val categoryId: Int?
) {
    val isNotValidName get() = name?.length !in 1..40
    val isNotValidDescription get() = description?.length !in 1..500
    val isNotValidPrice get() = price?.let { it < 1 } ?: false //엘비스연산자 가격이 1보다 작다면 false 값 반환 else 라면 넘어감.
    val isNotValidCategoryId get() = categoryId == null
}
