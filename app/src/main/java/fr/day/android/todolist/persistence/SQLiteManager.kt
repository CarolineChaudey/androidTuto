package fr.day.android.todolist

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * Created by Fode on 27/11/17.
 * Updated by Caroline
 */
class SQLiteManager(private val context: Context) : SQLiteOpenHelper(context, DATABASENAME,
        null, DATABASEVERSION) {


    companion object {

        private val DATABASENAME = "task.db"
        private val TABLENAME = "task"
        private var DATABASEVERSION = 1

        private var INSTANCE: SQLiteManager? = null

        @Synchronized
        fun getInstance(context: Context): SQLiteManager? {
            if (INSTANCE == null) {
                INSTANCE = SQLiteManager(context)
            }
            return INSTANCE
        }
    }
    override fun onCreate(db: SQLiteDatabase?) {
        db?.let {
            val query = "CREATE TABLE $TABLENAME(id INTEGER PRIMARY KEY, title TEXT, description TEXT);"
            it.execSQL(query)
            Log.v("@@@TESSSTTT", "task database created successfully")
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        if (oldVersion != newVersion) {
            val dropTableQuery = "DROP TABLE IF EXISTS $TABLENAME;"
            db?.let {
                it.execSQL(dropTableQuery)
                onCreate(it)
            }
        }
    }

    /*
    var tasks = mutableListOf<Task>()




    fun add(task: Task) {

        val db = this.writableDatabase
        db.beginTransaction()

        val values = ContentValues()
        values.put("title", task.title)
        values.put("description", task.description)

        try {
            db.insert(TABLENAME, null, values)
            db.setTransactionSuccessful()
        } catch (e: Exception) {
            Log.v("@@@ERROR", e.toString())
        } finally {
            db.endTransaction()
        }

    }

    fun loadTasks() {
        tasks.clear()
        val db = writableDatabase
        try {
            db.beginTransaction()
            var cursor = db.rawQuery("select * from $TABLENAME", null)
            if (cursor.moveToFirst()) {
                do {
                    tasks.add(Task(
                                    cursor.getString(1),
                                    cursor.getString(2)
                    ))
                } while (cursor.moveToNext())
            }

            db.setTransactionSuccessful()

        } catch (e: Exception) {
            Log.v("@@@ERROR", e.toString())
        } finally {
            db.endTransaction()
        }
    }

    fun updateTask(task: Task){

        val db = writableDatabase

        val contentValues = ContentValues()

        contentValues.put("title", task.title)
        contentValues.put("description", task.description)

        try{

            db.beginTransaction()
            db.update(TABLENAME, contentValues,"id=?", arrayOf("${task.id}"))

            db.setTransactionSuccessful()
            loadTasks()

        }catch(e: Exception){
            Log.v("@ERROR", e.toString())
        }finally {
            db.endTransaction()
        }

    }

    fun deleteTask(task: Task){

        val db = writableDatabase

        db.beginTransaction()

        try {

            //db.delete(TABLENAME, "id=?", arrayOf("${task.id}"))

            db.execSQL("DELETE FROM $TABLENAME WHERE id=${task.id}")

            loadTasks()

        }catch (e: Exception){
            Log.v("@ERROR while deleting", e.toString())
        }finally {
            db.endTransaction()
            db.close()
        }

    }

    */

}