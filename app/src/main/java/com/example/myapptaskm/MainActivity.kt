package com.example.myapptaskm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapptaskm.database.TaskDatabase
import com.example.myapptaskm.model.Task
import com.example.myapptaskm.repository.TaskRepository
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(),OnItemClickListener {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize repository
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        val repository = TaskRepository(taskDao)

        // Use TaskViewModelFactory to create ViewModel
        val factory = TaskViewModelFactory(repository)
        taskViewModel = ViewModelProvider(this, factory)[TaskViewModel::class.java]

        val adapter = TaskAdapter(listener = this)
        //val adapter = TaskAdapter(tasks = mutableListOf(), listener = this)



        val recyclerView: RecyclerView = findViewById(R.id.mainRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        taskViewModel.allTasks.observe(this, Observer { tasks: List<Task>? ->
            tasks?.let { adapter.addTasks(it) }
        })


        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            // Navigate to NewTaskActivity when FAB is clicked.
            val intent = Intent(this@MainActivity, NewTaskActivity::class.java)
            startActivity(intent)
        }
    }


    override fun onItemClick(task: Task) {
        val intent = Intent(this, TaskDetailActivity::class.java)
        intent.putExtra("task_id", task.id) // Pass the ID of the task to the detail Activity
        startActivity(intent)
    }


    override fun onUpdateClick(task: Task) {
        // For example, you can start an Activity to update the clicked task
        val intent = Intent(this, UpdateTaskActivity::class.java)
        intent.putExtra("task_id", task.id) // Pass the ID of the task to the update Activity
        startActivity(intent)
    }

    override fun onDeleteClick(task: Task) {
        taskViewModel.delete(task)
    }

}



