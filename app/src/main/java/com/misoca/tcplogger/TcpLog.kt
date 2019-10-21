package com.misoca.tcplogger

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*


@Entity(tableName = "tcp_log")
data class TcpLog(
    @PrimaryKey
    val id: Long?,
    val timestamp: Long,
    val message: String
)