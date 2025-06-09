package com.example.todoapp.DB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.todoapp.list

@Dao
interface todoDao {

     @Query("SELECT * FROM list")
    fun getAllTodo(): LiveData<List<list>>

    @Insert
    fun addTodo(todoDao: list)

    @Query("DELETE FROM list WHERE id = :id")
    fun deleteTodo(id:Int)
}