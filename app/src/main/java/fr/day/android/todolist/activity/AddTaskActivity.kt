package fr.day.android.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import fr.day.android.todolist.persistence.PrefsManager
import kotlinx.android.synthetic.main.activity_add_task.*
import kotlinx.android.synthetic.main.content_add_task.*

class AddTaskActivity : AppCompatActivity() {

    lateinit var prefs : PrefsManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        setSupportActionBar(toolbar)

        prefs = PrefsManager(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        add_button.setOnClickListener {
            val title = task_title_field.text.toString()
            val description = task_description_field.text.toString()

            /*
            prefs.getAllTasks()
            prefs.tasksList.items.add(Task(title, description))
            prefs.saveTask(prefs.tasksList)
            */

            var intent = Intent().apply {

                putExtra("new_task", Gson().toJson(Task(title, description)))

            }

            setResult(Activity.RESULT_OK, intent)

            finish()
        }
    }

}
