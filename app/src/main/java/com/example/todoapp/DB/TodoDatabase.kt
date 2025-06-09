package com.example.todoapp.DB

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.todoapp.list

@Database(entities = [list::class], version = 1)
@TypeConverters(Converters::class)
abstract class TodoDatabase:RoomDatabase() {


    companion object{
        const val NAME = "Todo_Db"
    }

    abstract fun getTodoDao(): todoDao
}