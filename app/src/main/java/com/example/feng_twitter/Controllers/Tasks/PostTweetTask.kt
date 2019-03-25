package com.example.feng_twitter.Controllers.Tasks

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import com.example.feng_twitter.AppInfo
import com.example.feng_twitter.Controllers.Tweet.Tweet
import com.example.feng_twitter.Controllers.Tweet.TweetActions
import com.example.feng_twitter.Data.Services.ServiceConnector
import com.example.feng_twitter.Data.Storage.TempStorage
import com.example.feng_twitter.Presentation.Callbacks.PostTweetTaskCallback
import com.google.gson.GsonBuilder
import java.lang.ref.WeakReference
import java.util.*

class PostTweetTask(
    private val mTweet: Tweet, private val mContext: WeakReference<AppCompatActivity>,
    private val mServiceConnector: ServiceConnector,
    private val mCallback: PostTweetTaskCallback
) : BaseSyncTask(mServiceConnector) {
    override fun doInBackground(vararg params: Void?): MutableMap<String, String> {
        val activity = this.mContext.get();
        if (activity != null) {
            this.saveToTemp(activity)
        }
        return this.mApiServiceProxy.postTweet(this.mTweet)
    }

    override fun onPostExecute(result: MutableMap<String, String>?) {
        this.mCallback.onFinished(result as HashMap<String, String>)
    }

    fun saveToTemp(activity: Activity) {
        val url = AppInfo.getTempUrl(activity)
        val tempStorage = TempStorage(activity)
        val jsonString = tempStorage.getStringFromTemp(url)
        var tweetList = TweetActions.parseStringToList(jsonString)
        val gson = GsonBuilder().setPrettyPrinting().create()
        tweetList.add(this.mTweet)
        tempStorage.writeStringToTemp(url, TweetActions.parseListToString(tweetList))
    }


}