package fr.day.android.todolist

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import fr.day.android.todolist.adapters.TaskAdapter
import kotlinx.android.synthetic.main.activity_list.*
import kotlinx.android.synthetic.main.content_list.*

class MainActivity : AppCompatActivity() {

    private lateinit var taskList: SQLiteManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        //val dataList = listOf(Task(1, "Faire le ménage", "Super crados"), Task(2, "Coder le tuto", "Ultra urgent"),
                //Task(3, "Faire du sport", "Je suis fatigué"), Task(4, "Réviser l'électro", "Je n'ai toujours rien compris à ce cours"))
        /*
        SQLiteManager.getInstance(this)?.let {
            dataList.forEach { task ->
                        it.add(task)
            }
        }
    */
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
}
