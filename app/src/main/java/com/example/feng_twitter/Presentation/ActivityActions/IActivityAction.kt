package com.example.feng_twitter.Presentation.ActivityActions

import android.support.design.widget.TextInputEditText

interface  IBrowseActivityAction{
    fun initialAppLaunch()
    fun loadTweetsView()
    fun logout()
    fun loadPostTweetsView()

}

interface IComposeActivityAction{
    fun getTextContent(editText : TextInputEditText):String
    fun postTweet()

}