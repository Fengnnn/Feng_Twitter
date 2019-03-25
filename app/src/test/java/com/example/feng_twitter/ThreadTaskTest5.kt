package com.example.feng_twitter

import com.example.feng_twitter.Controllers.AuthApi.Objects.UserCredential
import com.example.feng_twitter.Utility.ThreadTask.AwaitTask
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ThreadTaskTest5 {



    @Test
    fun awaitThreadTest() {
        println("Test AwaitThread with return within timeout")
        var count = 0;
        val awaitThread = AwaitTask()
        val mockObject = UserCredential("f","d")
        val runnable = Runnable {
            println("before sleep")
            count++
            Thread.sleep(2000)
            count++
            println("after sleep")
            awaitThread.awake(mockObject as Object)

        }
        Thread(runnable).start()

        awaitThread.waitForTime(mockObject as Object, 3000)

        assert(count==2)

    }
}
