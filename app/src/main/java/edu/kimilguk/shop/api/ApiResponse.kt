package edu.kimilguk.shop.api

/**
 * 스프링부트API에서 응답받은 데이터 클래스
 */
data class ApiResponse<T>(
    val success: Boolean,
    val data: T? = null,//T타입이 null이면 data에 null을 입력한다.
    val message: String? = null
) {
    companion object {//static 대신 companion 을 사용, object는 싱글톤 클래스로 실행과 동시에 1개의 객체가 생성된다.
        //개발자 에러함수 추가. reified 키워드는 인라인 함수호출 시 타입을 줄 수 있게 한다. 예, ApiResponse.error<Type>(...)
        inline fun <reified T> error(message: String? = null) = ApiResponse(false, null as T?, message)
    }
}
