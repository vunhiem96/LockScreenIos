package com.vunhiem.lockscreenios.screens.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.vunhiem.lockscreenios.R
import com.vunhiem.lockscreenios.screens.main.MainActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.splash_activity.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        loadData()
        splashScreen()
    }

    fun splashScreen() {
        val handler = android.os.Handler()
        handler.postDelayed({ screenRouter() }, 1000)
    }
    private fun loadData() {
        Glide.with(this).load(R.drawable.splash).into(img_splash)
    }

    private fun screenRouter() {
        var intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
