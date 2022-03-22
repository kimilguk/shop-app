package edu.kimilguk.shop

import android.preference.PreferenceManager

/**
 * SharedPreferences 파일저장 클래스로 싱클톤 객체로 사용한다.
 */
object Prefs {
    private const val TOKEN = "token"//const=static속성, val=final속성
    private const val REFRESH_TOKEN = "refresh_token"
    private const val USER_NAME = "user_name"
    private const val USER_ID = "user_id"

    val prefs by lazy {//lateinit 처럼 초기화 지연(val 전용,사용시 초기화 할 수 있다.)
        //SharedPreferences 객체를 앱 진입시(App.instance) 생성한다.
        PreferenceManager.getDefaultSharedPreferences(App.instance)
    }
    var token
        get() = prefs.getString(TOKEN, null)
        set(value) = prefs.edit()
            .putString(TOKEN, value)
            .apply()
    var refreshtoken
        get() = prefs.getString(REFRESH_TOKEN, null)
        set(value) = prefs.edit()
            .putString(REFRESH_TOKEN, value)
            .apply()
    var userName
        get() = prefs.getString(USER_NAME, null)
        set(value) = prefs.edit()
            .putString(USER_NAME, value)
            .apply()
    var userId
        get() = prefs.getLong(USER_ID, 0)
        set(value) = prefs.edit()
            .putLong(USER_ID, value)
            .apply()
}