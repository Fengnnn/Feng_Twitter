package com.example.feng_twitter.Data.Storage

import com.example.feng_twitter.Controllers.Tweet.Tweet
import com.example.feng_twitter.Controllers.Tweet.TweetActions
import org.junit.Assert
import org.junit.Test

class TweetActionTest {
    @Test
    fun parseListToString() {
        val result = TweetActions.parseListToString(getMockTweet())
        Assert.assertEquals(getMockString(), result)
    }

    @Test
    fun pareStringToList() {
        val result = TweetActions.parseStringToList(getMockString())
        val expected = getMockTweet()
        result.forEachIndexed { index, tweet ->
            Assert.assertEquals(expected[index].author, tweet.author)
            Assert.assertEquals(expected[index].content, tweet.content)
        }

    }

    private fun getMockTweet(): ArrayList<Tweet> {
        val tweetList = ArrayList<Tweet>()
        tweetList.add(Tweet("feng", "mock content 1"))
        tweetList.add(Tweet("feng", "mock content 2"))
        tweetList.add(Tweet("feng", "mock content 3"))
        return tweetList
    }

    private fun getMockString(): String {
        return "[\n" +
                "  {\n" +
                "    \"author\": \"feng\",\n" +
                "    \"content\": \"mock content 1\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"feng\",\n" +
                "    \"content\": \"mock content 2\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"author\": \"feng\",\n" +
                "    \"content\": \"mock content 3\"\n" +
                "  }\n" +
                "]"
    }
}