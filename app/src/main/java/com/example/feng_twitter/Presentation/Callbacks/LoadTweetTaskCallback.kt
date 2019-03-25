package com.example.feng_twitter.Presentation.Callbacks

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.feng_twitter.Controllers.Tweet.Tweet
import com.example.feng_twitter.Presentation.Fragments.TweetItemFragment
import com.example.feng_twitter.R
import java.lang.ref.WeakReference

class LoadTweetTaskCallback(private val mWeakActivity: WeakReference<AppCompatActivity>) :
    IApiRequestCallback<ArrayList<Tweet>> {

    override fun onFinished(response: ArrayList<Tweet>) {
        this.loadTweetItemFragment(response)
    }

    private fun loadTweetItemFragment(tweetList: ArrayList<Tweet>) {
        val activity = this.mWeakActivity.get() ?: return
        val args = Bundle()
        args.putSerializable(TweetItemFragment.ITEM_LIST_ARG, tweetList)
        val manager = activity.supportFragmentManager
        val transaction = manager.beginTransaction()
        val fragment = TweetItemFragment()
        fragment.arguments = args;
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}