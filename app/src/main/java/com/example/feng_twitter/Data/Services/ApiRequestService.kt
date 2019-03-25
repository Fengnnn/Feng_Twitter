package com.example.feng_twitter.Data.Services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.feng_twitter.Controllers.AuthApi.IFakedResponse
import com.example.feng_twitter.Controllers.AuthApi.Objects.FakeResponseBody
import com.example.feng_twitter.Controllers.AuthApi.Objects.FakedResponse
import com.example.feng_twitter.Controllers.AuthApi.Objects.UserCredential
import com.example.feng_twitter.Controllers.AuthApi.TwitterAuthSdk
import com.example.feng_twitter.Data.MapKeyValues
import com.example.feng_twitter.Data.Storage.SharedPreferenceStorage
import com.example.feng_twitter.IApiRequestService
import java.util.*

class ApiRequestService() : Service() {
    private val mBinder = ApiRequestBinder(this);


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return Service.START_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        return this.mBinder;
    }
}


class ApiRequestBinder(private val service: ApiRequestService) : IApiRequestService.Stub() {
    override fun authenticate(userName: String, passWord: String): MutableMap<String, String> {
        val sharedPreferenceOperations =
            SharedPreferenceStorage(this.service.baseContext)
        val userCredential = UserCredential(userName, passWord)
        val response: IFakedResponse = TwitterAuthSdk(sharedPreferenceOperations).authenticate(userCredential)
        var resultMap = HashMap<String, String>()
        resultMap.put(MapKeyValues.RESPONSE_CODE.value, response.responseBody.code.toString())
        resultMap.put(MapKeyValues.RESPONSE_BODY.value, response.responseBody.body)
        Thread.sleep(1000)
        return resultMap
    }

    override fun getListOfPost(): MutableMap<String, String> {
        var resultMap = HashMap<String, String>()
        val response: IFakedResponse = FakedResponse(true, FakeResponseBody(200, "has new post"))

        resultMap.put(MapKeyValues.RESPONSE_CODE.value, response.responseBody.code.toString())
        resultMap.put(MapKeyValues.RESPONSE_BODY.value, response.responseBody.body)
        Thread.sleep(1000)
        return resultMap
    }

    override fun postTweet(): MutableMap<String, String> {
        var resultMap = HashMap<String, String>()
        val response: IFakedResponse = FakedResponse(true, FakeResponseBody(200, "success"))

        resultMap.put(MapKeyValues.RESPONSE_CODE.value, response.responseBody.code.toString())
        resultMap.put(MapKeyValues.RESPONSE_BODY.value, response.responseBody.body)
        Thread.sleep(1000)
        return resultMap
    }

}



