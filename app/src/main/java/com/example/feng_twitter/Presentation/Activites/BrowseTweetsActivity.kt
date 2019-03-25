package com.example.feng_twitter.Presentation.Activites

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.example.feng_twitter.Data.ActivityRequestCode
import com.example.feng_twitter.Data.ActivityResultCode
import com.example.feng_twitter.Presentation.ActivityActions.BrowseActivityAction
import com.example.feng_twitter.Presentation.ActivityActions.IBrowseActivityAction
import com.example.feng_twitter.R
import kotlinx.android.synthetic.main.activity_main.*


class BrowseTweetsActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View) {
        if (v.id == R.id.fab) {
            this.mBrowseActivityAction.loadPostTweetsView()
        }
    }

    private lateinit var mBrowseActivityAction: IBrowseActivityAction
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fab.setOnClickListener(this)
        this.mBrowseActivityAction = BrowseActivityAction(this)
        this.mBrowseActivityAction.initialAppLaunch()
    }

    override fun onStart() {
        super.onStart()

    }

    override fun onResume() {
        super.onResume()
        this.mBrowseActivityAction.loadTweetsView()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        var result = true
        when (item.itemId) {
            com.example.feng_twitter.R.id.action_settings -> this.mBrowseActivityAction.logout()
            else -> {
                result = false
                super.onOptionsItemSelected(item)
            }
        }
        return result
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == ActivityRequestCode.LOGIN_REQUEST.code) {
            if (resultCode == ActivityResultCode.API_REQUEST_SUCCESS.code) {
                // RESUME HANDLE IT
            }

        }
    }


}
