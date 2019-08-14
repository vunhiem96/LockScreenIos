package com.vunhiem.lockscreenios.screens.password

import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import com.example.ibikenavigationkotlin.utils.AppConfig
import com.vunhiem.lockscreenios.R
import com.vunhiem.lockscreenios.service.MyService
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_password.*

class PasswordActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)
        onClick()
        switch()
    }
    fun switch() {
        var x: Boolean? = AppConfig.getStatusPassword(this)
        switch_pass.setOnCheckedChangeListener(this@PasswordActivity)
        switch_pass.isChecked= x!!

    }

    override fun onCheckedChanged(p0: CompoundButton?, p1: Boolean) {
//        var p1 = AppConfig.getStatusService(this)
        if (p1) {
            AppConfig.setStatusPassword(p1,this)

        } else {
            AppConfig.setStatusPassword(p1,this)

        }
    }
    private fun onClick() {
        img_back.setOnClickListener {
            finish()
        }
        ln_change_pass.setOnClickListener {
            var x = AppConfig.getPassord(this@PasswordActivity)
           if (x == null) {
                val intent = Intent(this,NewPassword::class.java)
                startActivity(intent)
       }else{
               val intent = Intent(this,ChangePassActivity::class.java)
               startActivity(intent)
           }
        }
    }
}
