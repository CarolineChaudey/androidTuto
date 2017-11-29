package fr.day.android.todolist

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {

    private lateinit var taskList : Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        Database.getInstance(this)?.writableDatabase

        fab.setOnClickListener { view ->
            val toAddTask = Intent(this, AddTaskActivity::class.java)
            startActivity(toAddTask)
        }


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }
}
