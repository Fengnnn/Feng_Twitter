package com.example.feng_twitter.Controllers.AuthApi

/**
 * Twitter has it's own login sdk. which  will handle the authentication session and token storage for you
 * or there is other open source sdk we could borrow to do the OAUTH process.
 * skipped the process to discover authentication url and load the Authentication GUI locally
 * Here is a simple interface to mock the Twitter login sdk
 */
interface ITwitterAuthSdk {
    fun getActiveSession(): IFakedResponse
    fun initialize(appConfig: IAppConfig)
    fun authenticate(userCredential: IUserCredential): IFakedResponse
    fun logOut()
}


interface IAppConfig {
    val clientId: String
    val clientSecret: String
}

interface IUserCredential {
    val userName: String
    val password: String
    fun isIdentical(userCredential: IUserCredential): Boolean
}

interface IFakedResponse {
    val ok: Boolean
    val responseBody: IFakedResponseBody
}

interface IFakedResponseBody {
    val code: Number
    val body: String
}
