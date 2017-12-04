package fr.day.android.todolist.model

import fr.day.android.todolist.persistence.DatabaseHelper
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.parseList
import org.jetbrains.anko.db.select

/**
 * Created by caroline on 27/11/17.
 */
data class Task(var id: Int?, var title: String, var description: String = "") {

    fun create(db: DatabaseHelper) {
        db.use {
            insert(db.TABLE_NAME, db.TITLE_COLUMN to title, db.DESCRIPTION_COLUMN to description)
        }
    }

    fun delete(db: DatabaseHelper) {
        db.use {
            delete(db.TABLE_NAME, "${db.ID_COLUMN} = ${id}", null)
        }
    }

    companion object {

        fun getAll(db: DatabaseHelper): List<Task> {
            return db.use {
                select(db.TABLE_NAME).exec { parseList(classParser()) }
            }
        }
    }
}