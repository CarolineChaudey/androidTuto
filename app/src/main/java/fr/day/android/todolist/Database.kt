package fr.day.android.todolist

/**
 * Created by caroline on 27/11/17.
 */
class Database private constructor() {
    private var tasks = mutableListOf<Task>()

    companion object {
        private val mInstance: Database = Database()

        @Synchronized
        fun getInstance(): Database {
            return mInstance
        }
    }

    fun add(task: Task) {
        tasks.add(task)
    }

    fun getAll(): List<Task> {
        return tasks
    }
}