package com.example.feng_twitter.Controllers.AppInitial

interface IOnAppInstall{
    fun storeCredential()
    fun registerApi()
}

interface IOnAppLaunch{
    fun startService()
    fun isLoggedIn():Boolean
}

interface IOnAppDestroy{

}

