package edu.kimilguk.shop.product.category
/**
 * 상품카테고리는 숫자이고, 숫자와 매칭되는 카테고리명을 가지는 데이터클래스
 * 스프링부트 API 와 입출력시 데이터를 임시 저장한다.
 */
data class CategoryVO(
    val id: Int, //카테고리 아이디
    val name: String //카테고리 명
)
//스프링부트API의 DB테이블데이터가 아닌 전역변수(프로퍼티)로 상품카테고리 리스트 생성
val categoryList = listOf( //코틀린 콜렉션 데이터생성
    CategoryVO(0, "여성의류"),
    CategoryVO(1, "남성의류"),
    CategoryVO(2, "가전제품"),
    CategoryVO(3, "생활용품"),
    CategoryVO(4, "도서"),
    CategoryVO(5, "반려동물용품"),
    CategoryVO(6, "식품")
)
