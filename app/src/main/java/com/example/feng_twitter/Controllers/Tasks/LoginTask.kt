package com.example.feng_twitter.Controllers.Tasks

import com.example.feng_twitter.Controllers.AuthApi.IUserCredential
import com.example.feng_twitter.Data.Services.ServiceConnector
import com.example.feng_twitter.Presentation.Callbacks.LoginTaskCallback
import java.util.*

class LoginTask(
    private val mUserCredential: IUserCredential, private val mServiceConnector: ServiceConnector,
    private val mCallback: LoginTaskCallback
) : BaseSyncTask(mServiceConnector) {
    override fun doInBackground(vararg params: Void?): MutableMap<String, String> {
        return this.mApiServiceProxy.runAuthentication(
            mUserCredential.userName,
            mUserCredential.password
        )
    }

    override fun onPostExecute(result: MutableMap<String, String>?) {
        this.mCallback.onFinished(result as HashMap<String, String>)
    }

}
