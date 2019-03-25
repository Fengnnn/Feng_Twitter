package com.example.feng_twitter.Utility.ThreadTask

/**
 * extend this task to wait for the task to finish in the given timeout
 */
open class AwaitTask {

    fun waitForTime(lock: java.lang.Object, timeout: Long) {
        synchronized(lock) {
            lock.wait(timeout)
        }
    }

    fun awake(lock: java.lang.Object) {
        synchronized(lock) {
            lock.notify()
        }
    }

}