package fr.day.android.todolist.persistence

import android.content.Context
import com.google.gson.Gson
import fr.day.android.todolist.model.Tasks

/**
 * PrefsManager -
 * @author guirassy
 * @version $Id$
 */
class PrefsManager(val context: Context) {

    private val PREFERENCES_TAG = "TODO_LIST_PREFERENCES"
    private val TASKS_LIST_TAG = "TASKS_LIST"

    private var gson = Gson()

    private var sharedPrefs = context.getSharedPreferences(PREFERENCES_TAG, Context.MODE_PRIVATE)

    private var sharedPrefsEditor = sharedPrefs.edit()

    lateinit var tasksList : Tasks

    fun saveTask(tasks: Tasks) {
        val tasksToJson = gson.toJson(tasks)
        sharedPrefsEditor.putString(TASKS_LIST_TAG, tasksToJson)
        sharedPrefsEditor.commit()
    }

    fun getAllTasks() {
        val jsonTasks = sharedPrefs.getString(TASKS_LIST_TAG, null)
        tasksList = gson.fromJson(jsonTasks, Tasks::class.java)
    }

}