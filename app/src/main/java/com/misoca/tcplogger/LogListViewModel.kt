package com.misoca.tcplogger

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LogListViewModel(application: Application): AndroidViewModel(application) {
    private val db:TcpLogDatabase = TcpLogDatabase.getInstance(application)
    internal val logs : LiveData<List<TcpLog>> = db.tcpLogDao().findAll()
    fun delete() {
        GlobalScope.launch {
            db.tcpLogDao().delete()
        }
    }
}