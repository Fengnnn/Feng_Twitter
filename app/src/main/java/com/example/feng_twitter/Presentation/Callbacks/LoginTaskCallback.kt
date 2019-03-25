package com.example.feng_twitter.Presentation.Callbacks

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.support.v7.app.AlertDialog
import com.example.feng_twitter.Data.ActivityResultCode
import com.example.feng_twitter.Data.ApiRequestResponseCode
import com.example.feng_twitter.Data.MapKeyValues
import java.util.*

class LoginTaskCallback(private val mContext: Context) : IApiRequestCallback<HashMap<String, String>> {
    override fun onFinished(responseMap: HashMap<String, String>) {
        val intent = Intent()

        val code = responseMap.get(MapKeyValues.RESPONSE_CODE.value)
        if (code != null && code.toInt() == ApiRequestResponseCode.HTTP_OK.code) {
            intent.putExtra("result", responseMap)
            (this.mContext as Activity).setResult(ActivityResultCode.API_REQUEST_SUCCESS.code, intent)
            (this.mContext).finish()
        } else {
            val message = responseMap.get(MapKeyValues.RESPONSE_BODY.value)
            this.showAlert(message)
        }
    }

    private fun showAlert(message: String?) {
        val dialog = AlertDialog.Builder(this.mContext)
        dialog.setMessage(message).setPositiveButton("OK", DialogInterface.OnClickListener { dialog, id ->
            dialog.dismiss()
        }).show()
    }

}