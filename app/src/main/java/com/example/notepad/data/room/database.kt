package com.example.notepad.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notepad.data.todo

@Database(entities = [todo::class]  ,version =1,exportSchema = false)
abstract class tododatabase : RoomDatabase() {
    abstract fun todoDao(): Todo_dao
           //to access the creation of database
            companion object {
                @Volatile
                private var INSTANCE:tododatabase ? = null
        fun getDatabase(context: Context) : tododatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                  tododatabase ::class.java,
                    "todo_db"

                ).build()
                INSTANCE =instance
                instance


            }
        }
            }
}