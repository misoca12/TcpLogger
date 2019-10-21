package com.misoca.tcplogger

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.net.ServerSocket
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.Socket


class TcpLoggingService : Service() {

    companion object {
        const val NOTIFICATION_ID = 1
        const val CHANNEL_ID = "TCP"
    }

    val server: ServerSocket by lazy {
        ServerSocket(SettingPref(applicationContext).portNo.toInt())
    }
    var socket: Socket? = null
    var job: Job? = null

    val db by lazy {
        TcpLogDatabase.getInstance(applicationContext)
    }

    override fun onBind(intent: Intent): IBinder? = null

    override fun onCreate() {
        super.onCreate()
        startForeground(NOTIFICATION_ID, NotificationCompat.Builder(applicationContext, CHANNEL_ID).run {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle(TcpLoggingService::class.java.simpleName)
            setContentText("TCP Server listening now.")
            build()
        })
        startTcpServer()
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        stopSelf()
    }

    private fun startTcpServer() {
        job?.cancel()
        job = GlobalScope.launch {
            while (true) {
                try {
                    socket = server.accept()
                    val reader = BufferedReader(InputStreamReader(socket?.getInputStream()))
                    while (true) {
                        reader.readLine()?.let {
                            Log.d("RECEIVE TCP", it)
                            db.tcpLogDao().insert(TcpLog(null, System.currentTimeMillis(), it))
                        } ?: break
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        }
    }

}
