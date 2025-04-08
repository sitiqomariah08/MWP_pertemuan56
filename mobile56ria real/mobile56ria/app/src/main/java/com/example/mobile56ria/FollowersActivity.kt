package com.example.mobile56ria

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class FollowersActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_followers)

        supportActionBar?.title = "Followers"
    }
}
