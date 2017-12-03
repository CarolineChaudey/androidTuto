package fr.day.android.todolist

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_add_task.toolbar
import kotlinx.android.synthetic.main.content_add_task.add_button
import kotlinx.android.synthetic.main.content_add_task.task_description_field
import kotlinx.android.synthetic.main.content_add_task.task_title_field

class AddTaskActivity : AppCompatActivity() {

    private var isForEdit = false

    private var responseIntent = Intent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        setSupportActionBar(toolbar)


        intent?.extras?.let {

            val rawTaskToUpdate = it.getString("edit_task")
            val taskToUpdate = Gson().fromJson(rawTaskToUpdate, Task::class.java)
            isForEdit = true

            task_title_field.setText(taskToUpdate.title)
            task_description_field.setText(taskToUpdate.description)

        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        add_button.setOnClickListener {

            val title = task_title_field.text.toString()
            val description = task_description_field.text.toString()

           val taskToSend =  Task(title, description)

            if(isForEdit) {
                responseIntent.putExtra("edit_task", Gson().toJson(taskToSend))
                setResult(Activity.RESULT_OK, responseIntent)
                finish()
            } else {
                responseIntent.putExtra("new_task", Gson().toJson(taskToSend))
                setResult(Activity.RESULT_OK, responseIntent)
                finish()
            }

        }
    }

}
