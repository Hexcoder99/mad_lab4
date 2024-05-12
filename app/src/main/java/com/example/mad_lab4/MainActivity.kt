package com.example.mad_lab4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mad_lab4.adapter.TaskListAdapter
import com.example.mad_lab4.database.DatabaseHelper
import com.example.mad_lab4.model.AddTask
import com.example.mad_lab4.model.TaskListModel

class MainActivity : AppCompatActivity() {

          lateinit var recycler_task : RecyclerView
          lateinit var btn_add : Button
          var taskListAdapter : TaskListAdapter ?= null
          var dbHandler : DatabaseHelper ?=null
          var tasklist : List<TaskListModel> = ArrayList<TaskListModel>()
          var linearLayoutManager : LinearLayoutManager ?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler_task = findViewById(R.id.rv_list)
        btn_add = findViewById(R.id.bt_add_items)

        dbHandler = DatabaseHelper(this)
        fetchlist()

        btn_add.setOnClickListener {
            val i = Intent(applicationContext, AddTask::class.java)
            startActivity(i)
        }

    }
    private fun fetchlist() {
        // Fetch the list of tasks from the database
        tasklist = dbHandler!!.getAllTask()

        // Create an adapter for the RecyclerView
        taskListAdapter = TaskListAdapter(tasklist, applicationContext)

        // Create an instance of LinearLayoutManager
        linearLayoutManager = LinearLayoutManager(applicationContext)

        // Set the LinearLayoutManager as the layout manager for the RecyclerView
        recycler_task.layoutManager = linearLayoutManager

        // Set the adapter for the RecyclerView
        recycler_task.adapter = taskListAdapter

        // Notify the adapter that the data has changed (if applicable)
        taskListAdapter?.notifyDataSetChanged()
    }

}