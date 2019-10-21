package com.misoca.tcplogger

import android.app.Application
import android.content.Intent

class TcpLoggerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startService(Intent(applicationContext, TcpLoggingService::class.java))
    }
}