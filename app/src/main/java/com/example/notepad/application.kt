package com.example.notepad

import android.app.Application

//application class is created so that it can be initialzed  when application is created
class Todo_application : Application() //inherting from application class
{
    override fun onCreate() {
        super.onCreate()

        //initialising application
        graph.Provide(this)

        //go to androidmanifest.xml to register ur application
        //android:name =" .Todo_application" and create home in ui package
    }
}