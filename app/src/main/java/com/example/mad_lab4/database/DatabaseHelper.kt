package com.example.mad_lab4.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null,DB_VERSION) {

    companion object{
        private val DB_NAME = "task"
        private val DB_VERSION = 1
        private val TABLE_NAME = "tasklist"
        private val ID = "id"
        private val TASK_NAME = "taskname"
        private val TASK_DETAILS = "taskdetails"
    }
    override fun onCreate(p0: SQLiteDatabase?){
        TODO("Nit yet implemented")
    }

    override fun onUpgrade(p0:SQLiteDatabase?, p1: Int, p2: Int){
        TODO("Not yet implemented")
    }
}