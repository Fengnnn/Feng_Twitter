package com.example.feng_twitter.Utility.ThreadTask

/**
 * extend this class to wait for the thread to finish
 */
open class ThreadTask(runnable: Runnable) : IThreadTask {

    private val mThread: Thread = Thread(runnable)

    override fun waitUntilComplete() {
        try {
            this.mThread.join()
        } catch (error: InterruptedException) {

        }
    }

    override fun start(): IThreadTask {
        this.mThread.start()
        return this;
    }


}

