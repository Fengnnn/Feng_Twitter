package com.example.feng_twitter.Data.Services

import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.example.feng_twitter.IApiRequestService
import com.example.feng_twitter.Utility.ThreadTask.AwaitTask

class ServiceConnector(private val mContext: Context, private val mServiceClass: Class<*>) : IServiceConnector,
    AwaitTask() {


    private lateinit var mBinder: IBinder;
    private var mConnection: ApiServiceConnection = ApiServiceConnection(this)
    override fun bindService() {
        mContext.bindService(getServiceIntent(), mConnection, Context.BIND_AUTO_CREATE);
        this.waitForTime(mConnection as Object, 2000)
        this.mBinder = this.mConnection.getBinder()
    }

    override fun getApiService(): IApiRequestService {
        return IApiRequestService.Stub.asInterface(this.mBinder);
    }

    override fun unBindService() {
        mContext.unbindService(this.mConnection)
    }

    private fun getServiceIntent(): Intent {
        return Intent(this.mContext, this.mServiceClass)
    }
}