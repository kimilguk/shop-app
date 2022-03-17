package edu.kimilguk.shop.api

import retrofit2.http.GET

/**
 * 스프링부트API 쇼핑 기능에 접근하는 인터페이스
 */
interface ShopApi {
    @GET("/api/hello")
    suspend fun hello(): ApiResponse<String>//함수앞에 suspend 키워드를 추가해서 비동기 통신용 서스펜딩(백그라운드 대기) 함수임을 명시한다
    companion object {//static 대신 companion 을 사용, object는 싱글톤 클래스로 실행과 동시에 1개의 객체가 생성된다.
        val instance = ApiGenerator() //new 키워드없이 통신객체생성
            .generate(ShopApi::class.java)//코틀린에서 더블콜론(::)은 리플렉션(참조)을 위해 사용합니다.
        //끝에 .java 를 사용한 이유는 JVM에서 실행시 Kclass 에서 자바클래스로 바꾸어 주기 위해서 이다.
    }
}