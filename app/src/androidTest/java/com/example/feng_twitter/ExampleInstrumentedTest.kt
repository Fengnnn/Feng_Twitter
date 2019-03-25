package com.example.feng_twitter

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.example.feng_twitter.Data.Storage.TempStorage
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun tempStorageTest(){
        val appContext = InstrumentationRegistry.getTargetContext()
        val tempStorage = TempStorage(appContext)
        val url = "test"
        val contentString = "test content string"
        tempStorage.createFile(url)
        tempStorage.writeStringToTemp(url,contentString)
        val result = tempStorage.getStringFromTemp(url)
        assertEquals(contentString, result)
    }
}
