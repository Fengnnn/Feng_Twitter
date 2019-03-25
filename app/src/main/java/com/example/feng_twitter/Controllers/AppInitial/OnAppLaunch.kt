package com.example.feng_twitter.Controllers.AppInitial

import android.content.Context
import com.example.feng_twitter.Controllers.AuthApi.TwitterAuthSdk
import com.example.feng_twitter.Data.Services.ApiRequestService
import com.example.feng_twitter.Data.Services.ServiceHandler
import com.example.feng_twitter.Data.Storage.ISharedPreferenceStorage
import com.example.feng_twitter.Data.Storage.SharedPreferenceStorage

class OnAppLaunch(private val mContext: Context) : IOnAppLaunch {
    override fun startService() {
        ServiceHandler.startService(this.mContext, ApiRequestService::class.java)
    }

    override fun isLoggedIn(): Boolean {
        val authSdk = TwitterAuthSdk(this.getSharedPreferenceOperations())
        val response = authSdk.getActiveSession()
        return response.ok
    }

    private fun getSharedPreferenceOperations(): ISharedPreferenceStorage {
        return SharedPreferenceStorage(this.mContext)
    }

}