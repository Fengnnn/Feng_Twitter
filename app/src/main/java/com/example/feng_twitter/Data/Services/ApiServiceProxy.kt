package com.example.feng_twitter.Data.Services

import android.os.RemoteException
import com.example.feng_twitter.Controllers.Tweet.ITweet
import com.example.feng_twitter.Utility.ThreadTask.AwaitTask
import com.example.feng_twitter.Utility.ThreadTask.ThreadTask
import java.util.*

/**
 * class to communicate with service to run request and return response back to controller layer
 */
class ApiServiceProxy(private val mServiceConnector: IServiceConnector) : AwaitTask() {

    fun runAuthentication(userName: String, password: String): MutableMap<String, String> {
        this.mServiceConnector.bindService()
        val self = this
        //default to authenticate
        var responseMap: MutableMap<String, String> = HashMap<String, String>();
        val runnable = Runnable {
            responseMap = self.mServiceConnector.getApiService().authenticate(userName, password)
                    as MutableMap<String, String>
        }
        ThreadTask(runnable).start().waitUntilComplete()
        this.mServiceConnector.unBindService()
        return responseMap
    }

    fun getListOfPost(): MutableMap<String, String> {
        this.mServiceConnector.bindService()
        val self = this
        //default to authenticate
        var responseMap: MutableMap<String, String> = HashMap<String, String>();
        val runnable = Runnable {
            try {
                responseMap = self.mServiceConnector.getApiService().getListOfPost()
                        as MutableMap<String, String>
            } catch (ex: RemoteException) {
                //handle exception

            }
        }
        ThreadTask(runnable).start().waitUntilComplete()
        this.mServiceConnector.unBindService()
        return responseMap

    }

    fun postTweet(tweet: ITweet): MutableMap<String, String> {
        this.mServiceConnector.bindService()
        val self = this
        //default to authenticate
        var responseMap: MutableMap<String, String> = HashMap<String, String>();
        val runnable = Runnable {
            try {
                responseMap = self.mServiceConnector.getApiService().postTweet()
                        as MutableMap<String, String>
            } catch (ex: RemoteException) {
                //handle exception

            }
        }
        ThreadTask(runnable).start().waitUntilComplete()
        this.mServiceConnector.unBindService()
        return responseMap

    }
}


