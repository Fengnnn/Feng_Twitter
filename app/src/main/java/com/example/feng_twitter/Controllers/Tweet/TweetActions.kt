package com.example.feng_twitter.Controllers.Tweet

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

object TweetActions {

    fun parseStringToList(jsonString: String): ArrayList<Tweet> {
        var tweetList = ArrayList<Tweet>()
        if (!jsonString.isEmpty()) {
            try {
                val gson = GsonBuilder().setPrettyPrinting().create()
                tweetList = gson.fromJson(jsonString, object : TypeToken<List<Tweet>>() {}.type)
            } catch (e: Exception) {

            }
        }
        return tweetList
    }

    fun parseListToString(tweetList: ArrayList<Tweet>): String {
        val gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(tweetList)
    }
}