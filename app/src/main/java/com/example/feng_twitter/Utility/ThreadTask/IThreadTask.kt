package com.example.feng_twitter.Utility.ThreadTask

interface IThreadTask {
    fun waitUntilComplete()
    fun start(): IThreadTask
}