package fr.day.android.todolist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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

        val data = SQLiteManager.getInstance(this)?.getAll()
        if (data != null) taskListView.adapter = TaskAdapter(this, data)

        fab.setOnClickListener {

            val toAddTask = Intent(this, AddTaskActivity::class.java)
            startActivity(toAddTask)
            /*
            SQLiteManager.getInstance(this)?.let {

                SQLiteManager.getInstance(this)?.let {
                    it.updateTask(Task(1, "Faire le tour du monde", "Very important"))
                }

                it.tasks.forEach {
                    Log.v("@@@ tasks list", "${it.id} \t ${it.title} \t ${it.description}")
                }

            }
            */
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        if (v.getId() == R.id.taskListView) {
            val lv = v as ListView
            val acmi = menuInfo as AdapterContextMenuInfo
            val obj = lv.getItemAtPosition(acmi.position) as Task

            menu.add("Delete " + obj.title)
        }
    }
}
