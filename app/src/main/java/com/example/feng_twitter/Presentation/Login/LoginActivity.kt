package com.example.feng_twitter.Presentation.Login

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import com.example.feng_twitter.AppInfo
import com.example.feng_twitter.Controllers.AuthApi.Objects.UserCredential
import com.example.feng_twitter.Data.Storage.SharedPreferenceStorage
import com.example.feng_twitter.R
import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var mLoginView: LoginViewAction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolbar)

    }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View? {
        val view = super.onCreateView(name, context, attrs)
        return view
    }

    override fun onStart() {
        super.onStart()
        SharedPreferenceStorage(this)
            .saveUserCredential(UserCredential(AppInfo.getPresetUserName(this), AppInfo.getPresetPassword(this)))
        var loginView = findViewById<LinearLayout>(R.id.login_view)
        this.mLoginView = LoginViewAction(this, loginView)
        var loginButton: LoginButton = findViewById<LoginButton>(R.id.login_button_2)
        loginButton.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        if (v == null) {
            return
        }
        if (v.id == R.id.login_button_2) {
            this.mLoginView.handleLogin()
        }
    }

}
