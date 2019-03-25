package com.example.feng_twitter.Data.Services

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.getSystemService


object ServiceHandler {
    fun startService(context: Context, serviceClass: Class<*>) {
        if (!ServiceHandler.isServiceRunning(serviceClass, context)) {
            context.startService(Intent(context, serviceClass))
        }

    }


    private fun isServiceRunning(serviceClass: Class<*>, context: Context): Boolean {
        val manager = getSystemService(context, serviceClass) ?: return false
        var runningServiceInfo = (manager as ActivityManager).getRunningServices(Integer.MAX_VALUE)
        for (service: ActivityManager.RunningServiceInfo in runningServiceInfo) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }
}