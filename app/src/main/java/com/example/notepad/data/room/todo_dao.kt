package com.example.notepad.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notepad.data.todo
import kotlinx.coroutines.flow.Flow

@Dao

// define a form of behaviour that the implementing type have to follow.
//contains declarartions for abstract class and methods for implementing.
//cannot stor e a state
interface Todo_dao {

    @Query("SELECT * FROM todo" )
    fun selectAll() : Flow<List<todo>>
    //a flow is a type that can emit multiple values sequentially, as opposed to suspend functions that return only a single value.
    // For example, flow is used  to receive live updates from a database.

    @Insert(onConflict = OnConflictStrategy.REPLACE) //constant to replace the old data and continue the transaction
    suspend fun insert(todo: todo)


    @Query("DELETE FROM todo WHERE id = :id")
    suspend fun delete(id :Long)

    @Query("DELETE FROM todo")
    suspend fun deleteAlltodo()


    //if id is not used then everything will be updated hence id = :id is used to limit the updation when id is matching
    @Query("UPDATE  todo SET isDone = :isDone WHERE id = :id")
    suspend fun update(isDone: Boolean, id:Long)
    
}