package com.example.feng_twitter.Presentation.ActivityActions

import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import com.example.feng_twitter.AppInfo
import com.example.feng_twitter.Controllers.Tasks.PostTweetTask
import com.example.feng_twitter.Controllers.Tweet.Tweet
import com.example.feng_twitter.Data.Services.ApiRequestService
import com.example.feng_twitter.Data.Services.ServiceConnector
import com.example.feng_twitter.Presentation.Activites.ComposeTweetActivity
import com.example.feng_twitter.Presentation.Callbacks.PostTweetTaskCallback
import kotlinx.android.synthetic.main.view_compose.*
import java.lang.ref.WeakReference

class ComposeActivityAction(private val mActivity: ComposeTweetActivity) : IComposeActivityAction {
    override fun getTextContent(editText: TextInputEditText): String {
        return editText.text.toString()
    }

    override fun postTweet() {
        val tweetContent = this.getTextContent(this.mActivity.editText)
        if (validateContent(tweetContent)) {
            //show error
            return
        }
        val tweet = Tweet(AppInfo.getPresetUserName(this.mActivity), tweetContent)
        this.executePost(tweet)

    }

    private fun validateContent(tweetContent: String): Boolean {
        return tweetContent.isEmpty()
    }

    private fun executePost(tweet: Tweet) {
        val weakActivity = WeakReference<AppCompatActivity>(this.mActivity)
        val callback = PostTweetTaskCallback(weakActivity)
        val serviceConnector = ServiceConnector(this.mActivity, ApiRequestService::class.java)
        val task = PostTweetTask(tweet, weakActivity, serviceConnector, callback)
        task.execute()
    }


}