package com.example.todoapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class TodoViewModel:ViewModel() {

    val todoDao = MainApplication.todoDatabase.getTodoDao()

    var todoList:LiveData<List<list>> = todoDao.getAllTodo()



    fun addTodo(name:String){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTodo(list(name=name, createdOn = Date.from(Instant.now())))
        }


    }
    fun deleteTodo(id:Int){
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodo(id)

        }

    }
}