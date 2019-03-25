package com.example.feng_twitter.Controllers.Tweet

import java.io.Serializable

class Tweet(override val author: String, override val content: String) : ITweet, Serializable {
}