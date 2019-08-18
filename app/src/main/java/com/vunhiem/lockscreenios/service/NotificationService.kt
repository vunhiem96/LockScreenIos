package com.vunhiem.lockscreenios.service

import android.app.Notification
import android.content.Context
import android.content.Intent
import android.os.Build
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import android.support.v4.media.session.PlaybackStateCompat.Actions
import android.app.PendingIntent




@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
class NotificationService : NotificationListenerService() {

    internal lateinit var context: Context

    override fun onCreate() {

        super.onCreate()
        context = applicationContext

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    override fun onNotificationPosted(sbn: StatusBarNotification) {

        val pack = sbn.packageName
        val extras = sbn.notification.extras
        val title = extras.getString("android.title")
        val text = extras.getCharSequence("android.text")!!.toString()

        Log.i("Package", pack)
        Log.i("Title", title!!)
        Log.i("Text", text)


        val msgrcv = Intent("Msg")


        msgrcv.putExtra("package", pack)
        msgrcv.putExtra("title", title)
        msgrcv.putExtra("text", text)

        LocalBroadcastManager.getInstance(context).sendBroadcast(msgrcv)


    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        Log.i("Msg", "Notification Removed")

    }
}