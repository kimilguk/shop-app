package edu.kimilguk.shop.api.response

/**
 * 스프링부트에서 반환받은 상품 리스트값을 안드로이드에서 처리하는 api
 * DTO(Data Transfer Object) 와 같은 임시 저장소 역할
 */
class ProductListItemResponse(
    val id: Long,
    val name: String,
    val description: String,
    val price: Int,
    val status: String,
    val sellerId: Long
) {
    
}