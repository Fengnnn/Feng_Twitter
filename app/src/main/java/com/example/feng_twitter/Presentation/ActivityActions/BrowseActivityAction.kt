package com.example.feng_twitter.Presentation.ActivityActions

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.example.feng_twitter.AppInfo
import com.example.feng_twitter.Controllers.AppInitial.OnAppLaunch
import com.example.feng_twitter.Controllers.AuthApi.TwitterAuthSdk
import com.example.feng_twitter.Controllers.Tasks.LoadTweetTask
import com.example.feng_twitter.Data.ActivityRequestCode
import com.example.feng_twitter.Data.Services.ApiRequestService
import com.example.feng_twitter.Data.Services.ServiceConnector
import com.example.feng_twitter.Data.Storage.SharedPreferenceStorage
import com.example.feng_twitter.Data.Storage.TempStorage
import com.example.feng_twitter.Presentation.Activites.ComposeTweetActivity
import com.example.feng_twitter.Presentation.Callbacks.LoadTweetTaskCallback
import com.example.feng_twitter.Presentation.Login.LoginActivity
import java.lang.ref.WeakReference


class BrowseActivityAction(private val mActivity: AppCompatActivity) : IBrowseActivityAction {
    override fun loadTweetsView() {
        val appLaunch = OnAppLaunch(this.mActivity)
        if (!appLaunch.isLoggedIn()) {
            this.doLogin()
        } else {
            val weakActivity = WeakReference<AppCompatActivity>(this.mActivity)
            val callback = LoadTweetTaskCallback(weakActivity)
            val serviceConnector = ServiceConnector(this.mActivity, ApiRequestService::class.java)
            val task = LoadTweetTask(weakActivity, serviceConnector, callback)
            task.execute()
        }

    }

    override fun logout() {
        TwitterAuthSdk(SharedPreferenceStorage(this.mActivity)).logOut()
        this.removeTemp()
        this.doLogin()
    }

    override fun loadPostTweetsView() {
        val intent = Intent(this.mActivity, ComposeTweetActivity::class.java)
        this.mActivity.startActivity(intent)
    }

    override fun initialAppLaunch() {
        val appLaunch = OnAppLaunch(this.mActivity)
        appLaunch.startService()
        this.createTempFile()
    }


    private fun doLogin() {
        val intent = Intent(this.mActivity, LoginActivity::class.java)
        this.mActivity.startActivityForResult(intent, ActivityRequestCode.LOGIN_REQUEST.code)
    }

    private fun removeTemp() {
        val tempStorage = TempStorage(this.mActivity)
        val url = AppInfo.getTempUrl(this.mActivity)
        tempStorage.deleteFile(url)
    }

    /**
     * create temp file to store new tweet
     */
    private fun createTempFile() {
        val tempStorage = TempStorage(this.mActivity)
        val url = AppInfo.getTempUrl(this.mActivity)
        tempStorage.createFile(url)
    }


}