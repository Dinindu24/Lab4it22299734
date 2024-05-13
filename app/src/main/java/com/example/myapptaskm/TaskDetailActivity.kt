package com.example.myapptaskm

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapptaskm.database.TaskDatabase
import com.example.myapptaskm.model.Task
import com.example.myapptaskm.repository.TaskRepository

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)

        // Initialize repository
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        val repository = TaskRepository(taskDao)

        // Use TaskViewModelFactory to create ViewModel
        val factory = TaskViewModelFactory(repository)
        taskViewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)

        // Get the task ID from the Intent extra
        val taskId = intent.getIntExtra("task_id", 0)

        taskViewModel.getTask(taskId).observe(this, Observer { task ->
            // Display the task details
            val textViewTaskDetail: TextView = findViewById(R.id.textViewTaskDetail)
            textViewTaskDetail.text = task.task
        })
    }

}
