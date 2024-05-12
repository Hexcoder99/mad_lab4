package com.example.mad_lab4.model

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mad_lab4.MainActivity
import com.example.mad_lab4.R
import com.example.mad_lab4.database.DatabaseHelper

class AddTask :AppCompatActivity() {

    lateinit var btn_save : Button
    lateinit var btn_del : Button
    lateinit var et_name : EditText
    lateinit var et_details :EditText
    var dbHandler : DatabaseHelper ? = null
    var isEditMode : Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)

        btn_save = findViewById(R.id.btn_save)
        btn_del = findViewById(R.id.btn_del)
        et_name = findViewById(R.id.et_name)
        et_details =findViewById(R.id.et_details)

        dbHandler = DatabaseHelper(this)

        if (intent !=null && intent.getStringExtra("Mode")=="E"){
            //update data
            isEditMode = true
            btn_save.text = "Update data"
            btn_del.visibility = View.VISIBLE
            val tasks : TaskListModel = dbHandler!!.getTask(intent.getIntExtra("Id",0))
            et_name.setText(tasks.name)
            et_details.setText(tasks.details)


    } else {
        //insert new data
        isEditMode = false
            btn_save.text = "save data"
            btn_del.visibility = View.GONE
    }
        btn_save.setOnClickListener {
            var success : Boolean = false
            val tasks : TaskListModel = TaskListModel()
            if(isEditMode){
                //update
                tasks.id =intent.getIntExtra("Id",0)
                tasks.name=et_name.text.toString()
                tasks.details=et_details.text.toString()

                success = dbHandler?.updateTask(tasks) as Boolean
                //


            }else{
                //insert
                tasks.name = et_name.text.toString()
                tasks.details = et_details.text.toString()

                success = dbHandler?.addTask(tasks) as Boolean
            }
            if(success){
                val i = Intent(applicationContext,MainActivity::class.java)
                startActivity(i)
                finish()
            }else{
                Toast.makeText(applicationContext,"Something went wrong",Toast.LENGTH_LONG).show()
            }
        }

        btn_del.setOnClickListener{
            val dialog = AlertDialog.Builder(this).setTitle("Info").setMessage("click yes if you want to delete")
                .setPositiveButton("YES", {dialog, i->
                    val success = dbHandler?.deleteTask(intent.getIntExtra("Id",0)) as Boolean
                    if (success)
                        finish()
                    dialog.dismiss()
                })
                .setPositiveButton("No",{ dialog , i ->
                    dialog.dismiss()
                })
            dialog.show()
        }
}
}