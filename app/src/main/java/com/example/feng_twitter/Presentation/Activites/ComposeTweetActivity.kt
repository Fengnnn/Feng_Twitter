package com.example.feng_twitter.Presentation.Activites

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.feng_twitter.Presentation.ActivityActions.ComposeActivityAction
import com.example.feng_twitter.Presentation.ActivityActions.IComposeActivityAction
import com.example.feng_twitter.R
import kotlinx.android.synthetic.main.activity_compose.*

class ComposeTweetActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mComposeActivityAction: IComposeActivityAction;
    override fun onClick(v: View?) {
        if (v != null && v.id == fab.id) {
            this.mComposeActivityAction.postTweet()
        } else {
            this.onBackPressed()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_compose)
        setSupportActionBar(composeToolbar)
        composeToolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_material)
        composeToolbar.setNavigationOnClickListener(this)
        this.mComposeActivityAction = ComposeActivityAction(this);
        fab.setOnClickListener(this)
    }


}
