package com.example.feng_twitter.Controllers.AuthApi.Objects

import com.example.feng_twitter.Controllers.AuthApi.IUserCredential

class UserCredential(override val userName: String, override val password: String) :
    IUserCredential {
    override fun isIdentical(userCredential: IUserCredential): Boolean {
        if (!this.userName.equals(userCredential.userName)) {
            return false;
        }

        if (!this.password.equals(userCredential.password)) {
            return false;
        }
        return true;
    }

}