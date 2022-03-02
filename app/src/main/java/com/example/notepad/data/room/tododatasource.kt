package com.example.notepad.data.room

import com.example.notepad.data.todo
import kotlinx.coroutines.Dispatchers

class Tododatasource(private val todoDao: Todo_dao) {

    //to select everything at once

    val selectAll = todoDao.selectAll()

    suspend fun inserttodo(todo: todo) { Dispatchers.IO.apply { todoDao.insert(todo)  } }

    suspend fun deletetodo(todo: todo) { Dispatchers.IO.apply { todoDao.delete(todo.id)  } }

    suspend fun updatetodo(isDone: Boolean, id:Long ) { Dispatchers.IO.apply { todoDao.update(isDone, id)  } }




}