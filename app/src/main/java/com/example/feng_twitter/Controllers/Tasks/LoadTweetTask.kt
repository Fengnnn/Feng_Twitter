package com.example.feng_twitter.Controllers.Tasks

import android.support.v7.app.AppCompatActivity
import com.example.feng_twitter.AppInfo
import com.example.feng_twitter.Controllers.Tweet.Tweet
import com.example.feng_twitter.Controllers.Tweet.TweetActions
import com.example.feng_twitter.Data.ApiRequestResponseCode
import com.example.feng_twitter.Data.MapKeyValues
import com.example.feng_twitter.Data.Services.ServiceConnector
import com.example.feng_twitter.Data.Storage.TempStorage
import com.example.feng_twitter.Presentation.Callbacks.IApiRequestCallback
import com.example.feng_twitter.R
import java.lang.ref.WeakReference

class LoadTweetTask(
    private val mContext: WeakReference<AppCompatActivity>, private val mServiceConnector: ServiceConnector,
    private val mCallback: IApiRequestCallback<ArrayList<Tweet>>
) : BaseSyncTask(mServiceConnector) {
    override fun doInBackground(vararg params: Void?): MutableMap<String, String> {
        return this.mApiServiceProxy.getListOfPost()
    }

    override fun onPostExecute(result: MutableMap<String, String>?) {
        super.onPostExecute(result)
        val activity = this.mContext.get()
        if (result == null || activity == null) {
            //call on failure( not implemented)
            return
        }
        val code = result.get(MapKeyValues.RESPONSE_CODE.value)
        if (code != null && code.toInt() == ApiRequestResponseCode.HTTP_OK.code) {
            //read list from cache File this should be a separate  loading task, but put it here to save time
            this.mCallback.onFinished(this.getTweetListFromTemp(activity))
        } else {
            //call on failure( not implemented)
        }
    }


    private fun getTweetListFromTemp(activity: AppCompatActivity): ArrayList<Tweet> {

        var tweetList = this.getPresetTweet(activity)
        var tempList = this.getTweetFromTemp(activity)
        tweetList.addAll(tempList)
        this.getPresetTweet(activity)
        return tweetList;
    }

    private fun getTweetFromTemp(activity: AppCompatActivity): ArrayList<Tweet> {
        val tempStorage = TempStorage(activity)
        val url = AppInfo.getTempUrl(activity)
        val jsonString = tempStorage.getStringFromTemp(url)
        return TweetActions.parseStringToList(jsonString)
    }

    fun getPresetTweet(activity: AppCompatActivity): ArrayList<Tweet> {
        var tweetList = ArrayList<Tweet>()
        val presetTweets = activity.resources.getStringArray(R.array.feng_tweet)
        presetTweets.forEach {
            val tweet = Tweet("Feng", it)
            tweetList.add(tweet)
        }
        return tweetList

    }
}