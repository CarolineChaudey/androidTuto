package fr.day.android.todolist.adapter

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import fr.day.android.todolist.MainActivity
import fr.day.android.todolist.R
import fr.day.android.todolist.Task
import kotlinx.android.synthetic.main.task_list_row.view.*
import java.text.FieldPosition

/**
 * TasksAdapter -
 * @author guirassy
 * @version $Id$
 */
class TasksAdapter(private val context: Context, private var tasks: MutableList<Task>) : BaseAdapter() {

    override fun getView(index: Int, view: View?, viewGroup: ViewGroup): View {


        var v : View? = view

        if(v == null){

            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val layout = inflater.inflate(R.layout.task_list_row, null) as ConstraintLayout
            layout.isLongClickable = true

            v = inflater.inflate(R.layout.task_list_row, null)
            v.task_title.text = tasks[index].title
            v.task_description.text = tasks[index].description

        }

        return v!!
    }

    override fun getItem(index: Int): Any {
        return tasks[index]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return tasks.size
    }

}