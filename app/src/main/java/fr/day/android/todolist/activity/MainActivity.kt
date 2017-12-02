package fr.day.android.todolist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import fr.day.android.todolist.adapters.TaskAdapter
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.content_list.*
import android.widget.AdapterView.AdapterContextMenuInfo
import android.view.ContextMenu
import android.view.View
import android.widget.ListView


class MainActivity : AppCompatActivity() {

    private lateinit var taskList: SQLiteManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)
        
        registerForContextMenu(taskListView)

       // val data = SQLiteManager.getInstance(this)?.loadTasks()
      //  if (data != null) taskListView.adapter = TaskAdapter(this, data)

        val test = listOf( Task(1, "Faire un thiép", "Humm Delicicous"),
                Task(2, "Faire un mafé", "Humm ultra délicious"))

        SQLiteManager.getInstance(this)?.let {
            test.forEach { task ->
                it.add(task)
            }
            it.updateTask(Task(2, "Go run", "It is better than eating"))
            it.tasks.forEach {
                Log.v("@@@ERROR", "${it.id} \t ${it.title} \t ${it.description}")
            }
        }


        fab.setOnClickListener {
            val toAddTask = Intent(this, AddTaskActivity::class.java)
            startActivity(toAddTask)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        /*
        if (v.getId() == R.id.taskListView) {
            val lv = v as ListView
            val acmi = menuInfo as AdapterContextMenuInfo
            val obj = lv.getItemAtPosition(acmi.position) as Task

            menu.add("Delete " + obj.title)
        }
        */
    }

}
