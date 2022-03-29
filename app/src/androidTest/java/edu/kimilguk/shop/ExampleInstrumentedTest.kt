package edu.kimilguk.shop

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 * 자동 생성된 테스트 에러가 나서 build.gradle 아래항목 auto import로 자동 추가처리 후 OK
 * implementation 'androidx.test.ext:junit-ktx:1.1.3'
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("edu.kimilguk.shop", appContext.packageName)
    }
}