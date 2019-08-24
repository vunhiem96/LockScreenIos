package com.vunhiem.lockscreenios.screens.main

import android.Manifest
import android.app.admin.DevicePolicyManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.ibikenavigationkotlin.utils.AppConfig
import com.suke.widget.SwitchButton
import com.vunhiem.lockscreenios.R
import com.vunhiem.lockscreenios.screens.password.NewPassword
import com.vunhiem.lockscreenios.screens.password.PasswordAuthentic
import com.vunhiem.lockscreenios.screens.privacy.PrivacyActivity
import com.vunhiem.lockscreenios.screens.wallpaper.Wallpaper
import com.vunhiem.lockscreenios.service.NotificationService
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        checkAndRequestPermissions()

        val intent = Intent(this@MainActivity, NotificationService::class.java)
        startService(intent)
        loadData()
        oncClick()
        switch()


    }

    private fun loadData() {
        Glide.with(this).load(R.drawable.backgroundlock_1080).into(img_main)
    }


    private fun oncClick() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this@MainActivity)) {
                val intent = Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:$packageName")
                )
                startActivityForResult(intent, 1234)
            }
        }
        rl_password.setOnClickListener {
            var x = AppConfig.getPassord(this@MainActivity)
            if (x == null) {
                val intent = Intent(this, NewPassword::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, PasswordAuthentic::class.java)
                startActivity(intent)
            }
        }
        rl_wallpaer.setOnClickListener {
            val intent = Intent(this, Wallpaper::class.java)
            startActivity(intent)
        }
        rl_noti.setOnClickListener {
            val intent = Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")
            startActivity(intent)
        }
        rl_feedback.setOnClickListener {
            val url = "https://www.facebook.com/vu.nhiem.5"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        rl_share.setOnClickListener {
            val url = "https://play.google.com/store/search?q=lock%20screen"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
        rl_privacy.setOnClickListener {
            val intent = Intent(this, PrivacyActivity::class.java)
            startActivity(intent)
        }
        rl_cancle_lock.setOnClickListener {
            val intent = Intent(DevicePolicyManager.ACTION_SET_NEW_PASSWORD)
            startActivity(intent)
        }
    }

    fun switch() {
        var switchMainStatus: Boolean? = AppConfig.getLock(this)
        btn_main.isChecked = switchMainStatus!!
        btn_main.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                AppConfig.setLock(isChecked, this@MainActivity)

            } else {
                AppConfig.setLock(isChecked, this@MainActivity)
            }
        }

//        var switchMainStatus: Boolean? = AppConfig.getLock(this)
//        switch_main.isChecked = switchMainStatus!!
//        switch_main.setOnCheckedChangeListener(object : SwitchButton.OnCheckedChangeListener {
//            override fun onCheckedChanged(view: SwitchButton?, isChecked: Boolean) {
//                if (isChecked) {
//                    AppConfig.setLock(isChecked, this@MainActivity)
//
//                } else {
//                    AppConfig.setLock(isChecked, this@MainActivity)
//                    Log.i("hoho1", "$isChecked")
//                }
//            }
//
//        })

    }


    private fun checkAndRequestPermissions() {

        val permissions = arrayOf(
            Manifest.permission.SYSTEM_ALERT_WINDOW,
            Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_PHONE_STATE


        )
        val listPermissionsNeeded = ArrayList<String>()
        for (permission in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission)
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toTypedArray(), 1)
        }
    }

}

//private fun SwitchButton.setOnCheckedChangeListener(mainActivity: MainActivity) {
//
//}
