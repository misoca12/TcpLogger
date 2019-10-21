package com.misoca.tcplogger

import android.content.Context
import android.content.SharedPreferences

class SettingPref(val context: Context) {

    companion object {
        private const val PREF_NAME = "setting"
        private const val PORT_NO = "PORT_NO"
    }
    private val preferences: SharedPreferences
        get() = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var portNo: String
        get() = preferences.getString(PORT_NO, "8080") ?: "8080"
        set(value) {
            preferences.edit()
                .putString(PORT_NO, value)
                .apply()
        }
}