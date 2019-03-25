package com.example.feng_twitter.Data.Storage

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import com.example.feng_twitter.Controllers.AuthApi.IUserCredential
import com.example.feng_twitter.Controllers.AuthApi.Objects.UserCredential
import com.example.feng_twitter.R

class SharedPreferenceStorage(context: Context) :
    ISharedPreferenceStorage {

    private val mSharedPreferences: SharedPreferences
    private val mResources: Resources
    private val mContext: Context
    override fun getUserCredential(): IUserCredential {
        val userName = this.get(R.string.sharedPreference_userName_key)
        val password = this.get(R.string.sharedPreference_password_key)
        return UserCredential(userName, password);
    }

    override fun saveUserCredential(userCredential: IUserCredential) {
        this.save(R.string.sharedPreference_userName_key, userCredential.userName)
        this.save(R.string.sharedPreference_password_key, userCredential.password)
    }

    init {
        this.mSharedPreferences = this.getTokenSharedPre(context)
        this.mResources = context.resources;
        this.mContext = context
    }

    override fun cleanAll() {
        this.mSharedPreferences.edit().clear().apply()
    }

    override fun save(resourceId: Int, stringToSave: String) {
        val keyString = this.mResources.getString(resourceId)
        this.mSharedPreferences.edit().putString(keyString, stringToSave).apply()

    }

    override fun get(resourceId: Int): String {
        val key = this.mResources.getString(resourceId)
        val stringValue = this.mSharedPreferences.getString(key, " ")
        return stringValue

    }

    private fun getTokenSharedPre(context: Context): SharedPreferences {
        val fileName = context.resources.getString(R.string.sharedPreference_twitter_mock);
        val sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE);
        return sharedPreferences;
    }
}