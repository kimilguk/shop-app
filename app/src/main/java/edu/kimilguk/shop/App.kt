package edu.kimilguk.shop

import android.app.Application

/**
 * Application=앱의 전역(진입) 클래스를 상속한 영역에 공통으로 사용할 저장 값 사용
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
    companion object {//static 대신 companion 을 사용, object는 싱글톤 클래스로 실행과 동시에 1개의 객체가 생성된다.
        lateinit var instance: App//var 전용 lateinit=초기화 지연은 사용할지 모르는 데이터를 미리 초기화할 필요가 없다.(메모리 성능향상)
    }
}