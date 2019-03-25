package com.example.feng_twitter.Data.Services

import android.os.RemoteException
import com.example.feng_twitter.Controllers.AuthApi.IFakedResponseBody
import com.example.feng_twitter.IApiRequestService

interface IServiceConnector {
    fun bindService()
    fun getApiService(): IApiRequestService
    fun unBindService()
}