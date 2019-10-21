package com.misoca.tcplogger

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TcpLogDao{
    @Query("SELECT * FROM tcp_log ORDER BY timestamp DESC")
    fun findAll(): LiveData<List<TcpLog>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tcpLog: TcpLog)
    @Query("DELETE FROM tcp_log")
    fun delete()
}