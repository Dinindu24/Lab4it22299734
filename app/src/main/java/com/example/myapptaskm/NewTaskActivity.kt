package com.example.myapptaskm

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapptaskm.R.id.recyclerViewTasks
import com.example.myapptaskm.model.Task

class NewTaskActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        recyclerView = findViewById(R.id.recyclerViewTasks)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Initialize adapter
        adapter = TaskAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val buttonAddTask: Button = findViewById(R.id.buttonAddTask)

        buttonAddTask.setOnClickListener {
            // Add a new task to the list
            val tasks = Task(1, "New Task", "Description", task = "tasks")


            adapter.addTask(tasks)

            // Add your logic here
        }
    }
}