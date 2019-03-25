package com.example.feng_twitter.Controllers.Tasks

import android.os.AsyncTask
import com.example.feng_twitter.Data.Services.ApiServiceProxy
import com.example.feng_twitter.Data.Services.ServiceConnector

abstract class BaseSyncTask(
    private val mServiceConnector: ServiceConnector
) : AsyncTask<Void, Void, MutableMap<String, String>>() {
    lateinit var mApiServiceProxy: ApiServiceProxy;
    override fun onPreExecute() {
        super.onPreExecute()
        this.mApiServiceProxy = ApiServiceProxy(this.mServiceConnector)
    }
}