package com.example.feng_twitter.Presentation.Login

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import com.example.feng_twitter.R

class LoginButton : Button {
    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.initialButton()
    }

    private fun initialButton() {
        this.setText(R.string.login_button_display)
    }
}

