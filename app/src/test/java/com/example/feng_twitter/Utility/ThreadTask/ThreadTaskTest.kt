package com.example.feng_twitter.Utility.ThreadTask

import com.example.feng_twitter.Controllers.AuthApi.Objects.UserCredential
import org.junit.Test

/**
 * Test some of the ThreadTask package function
 * each  class should have their own test file, skipped in here
 *
 *
 */
class ThreadTaskTest {

    @Test
   fun threadTaskTest(){
        println("Test threadTaskTest")
        var count = 0;
        val runnable = Runnable {
            println("before sleep")
            count++
            Thread.sleep(2000)
            count++
            println("after sleep")
        }

        var threadTaskObject = ThreadTask(runnable)
        threadTaskObject.start().waitUntilComplete()
        assert(count == 2)
    }


    @Test
    fun awaitTaskTest() {
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

        assert(count == 2)

    }


}
