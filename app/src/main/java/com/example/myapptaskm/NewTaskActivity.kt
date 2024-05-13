package com.example.myapptaskm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapptaskm.R.id.recyclerViewTasks
import com.example.myapptaskm.database.TaskDatabase
import com.example.myapptaskm.model.Task
import com.example.myapptaskm.repository.TaskRepository

class NewTaskActivity : AppCompatActivity() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)

        // Initialize  repository
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        val repository = TaskRepository(taskDao)

        // Use TaskViewModelFactory to create  ViewModel
        val factory = TaskViewModelFactory(repository)
        taskViewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)

        recyclerView = findViewById(R.id.recyclerViewTasks)

        // Initialize adapter
        adapter = TaskAdapter()



        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val buttonAddTask: Button = findViewById(R.id.buttonAddTask)

        buttonAddTask.setOnClickListener {
            // Get the input from the user
            val taskName = findViewById<EditText>(R.id.editTextTaskNameNew).text.toString()

            // Create a new Task object with the inputted text
            val newTask = Task(0, taskName)

            taskViewModel.insertTask(newTask)

            // Add the new task to the RecyclerView adapter
            adapter.addTasks(listOf(newTask))

            finish()

        }
    }
}
