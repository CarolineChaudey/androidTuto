package fr.day.android.todolist

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_task.*
import kotlinx.android.synthetic.main.content_add_task.*

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        add_button.setOnClickListener { view ->
            val title = task_title_field.text.toString()
            val description = task_description_field.text.toString()
            val task = Task(title, description)
            finish()
        }
    }

}
