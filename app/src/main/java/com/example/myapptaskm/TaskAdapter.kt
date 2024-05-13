package com.example.myapptaskm

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapptaskm.model.Task


interface OnItemClickListener {
    fun onItemClick(task: Task)
    fun onUpdateClick(task: Task)
    fun onDeleteClick(task: Task)
}
class TaskAdapter(private var tasks: List<Task> = listOf(), private val listener: OnItemClickListener? = null) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {


    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskView: TextView = itemView.findViewById(R.id.textView)
        val updateButton: Button = itemView.findViewById(R.id.buttonUpdate)
        val deleteButton: Button = itemView.findViewById(R.id.buttonDelete)

        init {
            itemView.setOnClickListener {
                listener?.onItemClick(tasks[adapterPosition])
            }
            updateButton.setOnClickListener {
                listener?.onUpdateClick(tasks[adapterPosition])
            }
            deleteButton.setOnClickListener {
                listener?.onDeleteClick(tasks[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val currentTask = tasks[position]
        holder.taskView.text = currentTask.task

        holder.itemView.setOnClickListener {
            listener?.onItemClick(currentTask)
        }
    }


    override fun getItemCount(): Int {
        return tasks.size
    }



    fun addTasks(tasks: List<Task>) {
        this.tasks = tasks.toMutableList() // Convert to mutable list if needed
        notifyDataSetChanged()
    }








}





