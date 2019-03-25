package com.example.feng_twitter

import android.content.Context

object AppInfo {

    fun getPresetUserName(context: Context): String {
        return context.resources.getString(R.string.preset_user_name)
    }

    fun getPresetPassword(context: Context): String {
        return context.resources.getString(R.string.preset_user_password)
    }

    fun getTempUrl(context: Context): String {
        return context.resources.getString(R.string.trov_temp_url)
    }
}