package fr.day.android.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import com.google.gson.Gson
import fr.day.android.todolist.adapter.TasksAdapter
import fr.day.android.todolist.model.Tasks
import fr.day.android.todolist.persistence.PrefsManager
import kotlinx.android.synthetic.main.activity_list.fab
import kotlinx.android.synthetic.main.activity_list.toolbar
import kotlinx.android.synthetic.main.content_list.taskListView


class MainActivity : AppCompatActivity() {

    private lateinit var tasksAdapter: TasksAdapter
    lateinit var prefs: PrefsManager


    val CREATE_TASK_REQUEST_CODE = 1
    val EDIT_TASK_REQUEST_CODE = 2

    lateinit var taskToEdit: Task

    var tasks: MutableList<Task>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        prefs = PrefsManager(this)

        registerForContextMenu(taskListView)

        var tasksList = Tasks(mutableListOf(Task("Faire un thiép", "Humm Delicicous"),
                Task("Faire un mafé", "Humm ultra délicious")))


        prefs.getAllTasks()?.let {

            tasks = it.items
            tasksAdapter = TasksAdapter(this, tasks!!)
            taskListView.adapter = tasksAdapter

        }


        fab.setOnClickListener {
            val toAddTask = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(toAddTask, CREATE_TASK_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        when (requestCode) {

            EDIT_TASK_REQUEST_CODE -> {

                if (resultCode == Activity.RESULT_OK) {

                    val rawEditedTask = data?.extras?.getString("edit_task")
                    val editedTask = Gson().fromJson(rawEditedTask, Task::class.java)

                    tasks!!.add(editedTask)
                    prefs.saveTask(Tasks(tasks!!))

                    recreate()
                }
            }

            CREATE_TASK_REQUEST_CODE -> {

                if (resultCode == Activity.RESULT_OK) {

                    val rawNewTask = data?.extras?.getString("new_task")
                    val newTask = Gson().fromJson(rawNewTask, Task::class.java)
                    tasks!!.add(newTask)
                    prefs.saveTask(Tasks(tasks!!))

                    recreate()

                }
            }
        }

    }


    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menu?.let {
            it.add(0, 1, 0, "MODIFIER").setIcon(R.drawable.ic_edit)
            it.add(0, 2, 1, "SUPPRIMER")
        }

    }


    override fun onContextItemSelected(item: MenuItem): Boolean {

        item?.let { menuItem ->

            val menuInfo = (menuItem.menuInfo as AdapterView.AdapterContextMenuInfo?)

            menuInfo?.let { meunuInfo ->

                when (menuItem.title) {

                    "MODIFIER" -> {
                        val taskToUpdate = tasks!![menuInfo.position]
                        val toEditTask = Intent(this, AddTaskActivity::class.java)
                        toEditTask.putExtra("edit_task", Gson().toJson(taskToUpdate))

                        tasks!!.remove(taskToUpdate)


                        startActivityForResult(toEditTask, EDIT_TASK_REQUEST_CODE)
                    }

                    "SUPPRIMER" -> {
                        tasks!!.removeAt(menuInfo.position)
                        prefs.saveTask(Tasks(tasks!!))
                        tasksAdapter.notifyDataSetChanged()
                    }
                    else -> {
                    }
                }
            }
        }

        return super.onContextItemSelected(item)
    }

}
