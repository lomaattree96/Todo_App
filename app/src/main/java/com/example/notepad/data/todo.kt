package com.example.notepad.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//entity will make a class to have a  mapping sqlite table in databsse

//data class
@Entity
data class todo(
    val todo: String,
    val time: String,
    val isDone: Boolean = false,
    //primary key can be kept as true always
    @PrimaryKey(autoGenerate = true)
    val id : Long =0,
)
