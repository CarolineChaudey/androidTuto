package fr.day.android.todolist

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

/**
 * Created by caroline on 27/11/17.
 */
class Database(val context: Context) : SQLiteOpenHelper(context, DATABASENAME,
        null, DATABASEVERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {
            val query = "CREATE TABLE task(taskId INTEGER, title TEXT, description TEXT)"
            it.execSQL(query)
            Log.v("@@@TESSSTTT","task database created successfully")
        }
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var tasks = mutableListOf<Task>()

    companion object {
        private val DATABASENAME = "task"
        private val DATABASEVERSION = 1

        private var INSTANCE: Database? = null

        @Synchronized
        fun getInstance(context: Context): Database? {
            if (INSTANCE == null){
                INSTANCE = Database(context)
            }
            return INSTANCE
        }

    }

    fun add(task: Task) {
        this.writableDatabase
        tasks.add(task)
    }

    fun getAll(): List<Task> {
        return tasks
    }
}