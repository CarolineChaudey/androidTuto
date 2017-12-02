package fr.day.android.todolist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.content_list.*
import fr.day.android.todolist.adapter.TasksAdapter

import kotlinx.android.synthetic.main.activity_list.view.*
import kotlinx.android.synthetic.main.content_list.*


class MainActivity : AppCompatActivity() {

    private lateinit var tasksAdapter: TasksAdapter
    private lateinit var tasks: MutableList<Task>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)
        
        //registerForContextMenu(taskListView)

        val test = listOf(Task(1, "Faire un thiép", "Humm Delicicous"),
                Task(2, "Faire un mafé", "Humm ultra délicious"))

        SQLiteManager.getInstance(this)?.let {

            test.forEach { task ->
                it.add(task)
            }

            //it.updateTask(Task(2, "Go run", "It is better than eating"))

           // it.deleteTask(Task(1, "Faire un thiép", "Humm Delicicous"))

            it.loadTasks()

            it.tasks.forEach {
                Log.v("@@@TEST", "${it.id} \t ${it.title} \t ${it.description}")
            }

            //tasks = it.tasks

            tasksAdapter = TasksAdapter(this, it.tasks)
            taskListView.adapter = tasksAdapter

        }



        fab.setOnClickListener {
            val toAddTask = Intent(this, AddTaskActivity::class.java)
            startActivity(toAddTask)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }

}
