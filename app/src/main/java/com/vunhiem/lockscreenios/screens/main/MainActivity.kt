package com.vunhiem.lockscreenios.screens.main

import android.Manifest
import android.app.admin.DevicePolicyManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.ibikenavigationkotlin.utils.AppConfig
import com.vunhiem.lockscreenios.screens.password.NewPassword
import com.vunhiem.lockscreenios.screens.password.PasswordAuthentic
import com.vunhiem.lockscreenios.screens.wallpaper.Wallpaper
import com.vunhiem.lockscreenios.service.MyService
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import android.app.Activity
import android.net.Uri
import android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION
import android.provider.Settings.canDrawOverlays
import android.os.Build
import android.provider.Settings
import android.widget.Toast


class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.vunhiem.lockscreenios.R.layout.activity_main)
        checkAndRequestPermissions()
        switch()
        disableLockSreenSystem()
        oncClick()


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
            }else{
            val intent = Intent(this, PasswordAuthentic::class.java)
            startActivity(intent)}
        }
        rl_wallpaer.setOnClickListener {
            val intent = Intent(this, Wallpaper::class.java)
            startActivity(intent)
        }
        rl_noti.setOnClickListener {
            Toast.makeText(this,"function is developing",Toast.LENGTH_LONG).show()
        }
    }

    fun switch() {
        switch_main.setOnCheckedChangeListener(this)

    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
        if (p1) {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        } else {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }
    }
    private fun checkAndRequestPermissions() {

        val permissions = arrayOf(
            Manifest.permission.SYSTEM_ALERT_WINDOW,
            Manifest.permission.WRITE_SETTINGS,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
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
    fun disableLockSreenSystem() {
        rl_cancle_lock.setOnClickListener {
            val intent = Intent(DevicePolicyManager.ACTION_SET_NEW_PASSWORD)
            startActivity(intent)
        }
    }

}
