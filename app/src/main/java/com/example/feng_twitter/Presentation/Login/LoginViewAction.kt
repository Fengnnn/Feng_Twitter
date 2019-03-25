package com.example.feng_twitter.Presentation.Login

import android.content.Context
import android.widget.LinearLayout
import android.widget.TextView
import com.example.feng_twitter.Controllers.AuthApi.IUserCredential
import com.example.feng_twitter.Controllers.AuthApi.Objects.UserCredential
import com.example.feng_twitter.Controllers.Tasks.LoginTask
import com.example.feng_twitter.Data.Services.ApiRequestService
import com.example.feng_twitter.Data.Services.ServiceConnector
import com.example.feng_twitter.Presentation.Callbacks.LoginTaskCallback
import com.example.feng_twitter.R

class LoginViewAction(private val mContext: Context, private val mLoginView: LinearLayout) {


    fun handleLogin() {
        val credential = this.getCredentialFromInput()
        if (this.validateInput(credential)) {
            this.doLogin()
        } else {
            this.showErrorMessage()
        }

    }

    private fun doLogin() {
        val credential = this.getCredentialFromInput()
        val callback = LoginTaskCallback(this.mContext)
        val serviceConnector = ServiceConnector(this.mContext, ApiRequestService::class.java)
        val task = LoginTask(credential, serviceConnector, callback)
        task.execute()

    }


    private fun getCredentialFromInput(): com.example.feng_twitter.Controllers.AuthApi.IUserCredential {
        val userName = this.getUserNameFromView()
        var password = this.getPassWordFromView()
        return UserCredential(userName, password)
    }

    private fun getUserNameFromView(): String {
        val userNameTextView = this.mLoginView.findViewById<TextView>(R.id.userName)
        return userNameTextView.text.toString();
    }

    private fun getPassWordFromView(): String {
        val userPasswordView = this.mLoginView.findViewById<TextView>(R.id.password)
        return userPasswordView.text.toString();
    }

    private fun validateInput(credential: IUserCredential): Boolean {
        return true;
    }

    private fun showErrorMessage() {
        //to do show error message under login view

    }
}