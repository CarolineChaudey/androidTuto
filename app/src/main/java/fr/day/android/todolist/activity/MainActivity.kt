package fr.day.android.todolist.activity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.content_list.*
import android.widget.AdapterView.AdapterContextMenuInfo
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.ListView
import android.widget.Toast
import fr.day.android.todolist.R
import fr.day.android.todolist.adapters.TaskAdapter
import fr.day.android.todolist.model.Task
import fr.day.android.todolist.persistence.DatabaseHelper
import java.util.logging.Logger


class MainActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper
    private var adapter: TaskAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)
        
        registerForContextMenu(taskListView)
        db = DatabaseHelper.getInstance(this)
        val data = Task.getAll(db)
        Logger.getLogger(this.javaClass.name).warning(data.toString())
        adapter = TaskAdapter(this, data)
        taskListView.adapter = adapter

        fab.setOnClickListener {
            val toAddTask = Intent(this, AddTaskActivity::class.java)
            startActivity(toAddTask)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        return false
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo) {
        if (v.id == R.id.taskListView) {
            val lv = v as ListView
            val acmi = menuInfo as AdapterContextMenuInfo
            val obj = lv.getItemAtPosition(acmi.position) as Task

            menu.add("Delete")
        }
    }

    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val menuInfos = item?.menuInfo as AdapterContextMenuInfo
        when (item?.title) {
            "Delete" -> {
                val taskToDelete = adapter?.mList?.get(menuInfos.position)
                taskToDelete?.delete(db)
                refreshTaskList()
                Toast.makeText(this, "To delete", Toast.LENGTH_SHORT).show()
            }
            else -> return false
        }
        return true
    }

    fun refreshTaskList() {
        super.onResume()
        adapter?.mList = Task.getAll(db)
        adapter?.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        refreshTaskList()
    }
}
