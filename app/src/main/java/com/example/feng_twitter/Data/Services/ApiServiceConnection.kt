package com.example.feng_twitter.Data.Services

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import com.example.feng_twitter.Utility.ThreadTask.AwaitTask

class ApiServiceConnection(private val mAwaitThread: AwaitTask) : ServiceConnection {
    lateinit var mBinder: IBinder
    override fun onServiceDisconnected(name: ComponentName?) {
    }

    override fun onServiceConnected(name: ComponentName?, service: IBinder) {
        this.mBinder = service
        this.mAwaitThread.awake(this as Object)
    }

    fun getBinder(): IBinder {
        return this.mBinder
    }
}



