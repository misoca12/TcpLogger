package com.misoca.tcplogger

import android.content.Context
import androidx.room.*
import androidx.room.Database

@Database(entities = [TcpLog::class], version = 1)
abstract class TcpLogDatabase: RoomDatabase() {
    abstract fun tcpLogDao(): TcpLogDao

    companion object{
        private var instance: TcpLogDatabase? = null
        fun getInstance(context: Context): TcpLogDatabase{
            if (instance == null){
                instance = Room.databaseBuilder(
                    context,
                    TcpLogDatabase::class.java,
                    "roomdb")
                    .build()
            }

            return instance as TcpLogDatabase
        }
    }
}