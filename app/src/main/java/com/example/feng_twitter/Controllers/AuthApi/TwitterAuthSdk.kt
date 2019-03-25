package com.example.feng_twitter.Controllers.AuthApi


import com.example.feng_twitter.Controllers.AuthApi.Objects.FakedObjects
import com.example.feng_twitter.Data.Storage.ISharedPreferenceStorage
import com.example.feng_twitter.R

class TwitterAuthSdk(private val mSharedPreferenceOperations: ISharedPreferenceStorage) :
    ITwitterAuthSdk {

    /**
     * Remove token from shareReference
     */
    override fun logOut() {
        this.mSharedPreferenceOperations.cleanAll()
    }

    /**
     * get token from shareReference
     */
    override fun getActiveSession(): IFakedResponse {
        val accessToken = this.mSharedPreferenceOperations.get(R.string.sharedPreference_token_key);
        if (this.validateToken(accessToken)) {
            return FakedObjects.getFakedSuccessResponse()
        } else {
            return FakedObjects.getFakedFailedResponse()
        }

    }

    /**
     * initialize the app with client id and client secret
     * skipped the step here
     */
    override fun initialize(appConfig: IAppConfig) {
    }

    override fun authenticate(userCredential: IUserCredential): IFakedResponse {
        val savedCredential = this.mSharedPreferenceOperations.getUserCredential()
        var response: IFakedResponse
        if (savedCredential.isIdentical(userCredential)) {
            response = FakedObjects.getFakedSuccessResponse();
            //mock get new access token from api, skipped actual api request with code exchange
            val accessToken = response.responseBody.body
            this.mSharedPreferenceOperations.save(R.string.sharedPreference_token_key, accessToken)

        } else {
            response = FakedObjects.getFakedFailedResponse()

        }
        return response;
    }

    private fun validateToken(token: String): Boolean {
        if (token.isEmpty() || token.isBlank()) {
            return false
        }
        return true;
    }

}