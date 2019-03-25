package com.example.feng_twitter.Presentation.Callbacks

import android.support.v7.app.AppCompatActivity
import com.example.feng_twitter.Data.ApiRequestResponseCode
import com.example.feng_twitter.Data.MapKeyValues
import java.lang.ref.WeakReference
import java.util.*

class PostTweetTaskCallback(private val mWeakActivity: WeakReference<AppCompatActivity>) :
    IApiRequestCallback<HashMap<String, String>> {
    override fun onFinished(response: HashMap<String, String>) {
        val code = response.get(MapKeyValues.RESPONSE_CODE.value)
        if (code != null && code.toInt() == ApiRequestResponseCode.HTTP_OK.code) {
            val activity = this.mWeakActivity.get()
            if (activity != null) {
                activity.onBackPressed()
            }
        } else {
            //show error
        }
    }
}