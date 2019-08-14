package com.example.ibikenavigationkotlin.utils

import android.content.Context

object AppConfig {
    val PREF_NAME = "Android005"
    fun setPassword(passWord: String, context: Context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("password", passWord)
        editor.apply()
    }

    fun getPassord(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString("password", null)
    }
    fun setStatusPassword(serviceStatus:Boolean,context: Context){
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("serviceStatus", serviceStatus)
        editor.apply()
    }
    fun getStatusPassword(context: Context): Boolean? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("serviceStatus", false)
    }
    fun setIdWallPaper(id: String?, context: Context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("anh", id)
        editor.apply()
    }

    fun getIdWallPaper(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString("anh", null)
    }
    fun setIdWallPaperUri(idUri: String?, context: Context) {
        context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("idwallpaperuri", idUri)
        editor.apply()
    }

    fun getIdWallPaperUri(context: Context): String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString("idwallpaperuri", null)
    }
}