package com.example.notepad

import android.content.Context
import androidx.room.RoomDatabase
import com.example.notepad.data.room.Tododatasource
import com.example.notepad.data.room.tododatabase
import com.example.notepad.graph.database

//graph is used to create our datasources

object graph {
    //to initialise the dependancy lateinit var is made which is used to hold up the database
    lateinit var database: tododatabase
        private set

    //to get the data sources todorepo is made
    // by lazy is used so that when it can be initialized when  it is needed and we can call the database
    val todorepo by lazy {

        //we can call the database and access the todo_dao which is created
        Tododatasource(database.todoDao())
    }


    //to access and initialise the database
    fun Provide(context: Context) {
        database = tododatabase.getDatabase(context)
    }
}