package com.example.feng_twitter.Presentation.Callbacks


interface IApiRequestCallback<T>{
    fun onFinished(response:T)
}