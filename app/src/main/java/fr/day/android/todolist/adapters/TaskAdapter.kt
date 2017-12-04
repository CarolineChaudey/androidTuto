package fr.day.android.todolist.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import fr.day.android.todolist.R
import fr.day.android.todolist.model.Task

/**
 * Created by caroline on 02/12/17.
 */
class TaskAdapter(var context: Context, var mList: List<Task>): BaseAdapter() {

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view: View?
        val mInflator = LayoutInflater.from(context)
        view = when(p1) {
            null -> mInflator.inflate(R.layout.task_list_row, p2, false)
            else -> p1
        }
        // filling the view
        val task = mList.get(p0)
        val titleZone = view?.findViewById<TextView>(R.id.titleZone) as TextView
        val descriptionZone = view.findViewById<TextView>(R.id.descriptionZone) as TextView
        titleZone.text = task.title
        descriptionZone.text = task.description

        return view
    }

    override fun getItem(p0: Int): Any {
        return mList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return mList.size
    }
}