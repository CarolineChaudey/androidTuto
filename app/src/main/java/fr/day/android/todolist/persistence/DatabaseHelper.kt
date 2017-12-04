package fr.day.android.todolist.persistence

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by caroline on 04/12/17.
 */
class DatabaseHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "TodoListDatabase", null, 1) {

    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    val TABLE_NAME = "Task"
    val ID_COLUMN = "id"
    val TITLE_COLUMN = "title"
    val DESCRIPTION_COLUMN = "desciption"

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(TABLE_NAME, true, ID_COLUMN to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                TITLE_COLUMN to TEXT, DESCRIPTION_COLUMN to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.dropTable("Task", true)
    }
}