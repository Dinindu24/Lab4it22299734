package com.example.myapptaskm

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myapptaskm.database.TaskDatabase
import com.example.myapptaskm.model.Task
import com.example.myapptaskm.repository.TaskRepository

class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var taskViewModel: TaskViewModel
    private lateinit var editTextTaskName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_task)

        // Initialize repository
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        val repository = TaskRepository(taskDao)

        // Use TaskViewModelFactory to create ViewModel
        val factory = TaskViewModelFactory(repository)
        taskViewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)

        // Get the task ID from the Intent extra
        val taskId = intent.getIntExtra("task_id", 0)

        // Retrieve the task from the database using the task ID
        taskViewModel.getTask(taskId).observe(this, Observer { task ->
            // Display the task details in an editable form
            editTextTaskName = findViewById(R.id.editTextTaskName)
            editTextTaskName.setText(task.task)
        })

        val buttonSave: Button = findViewById(R.id.buttonSave)
        buttonSave.setOnClickListener {
            // Update the task and go back to MainActivity
            val updatedTask = Task(taskId, editTextTaskName.text.toString())
            taskViewModel.update(updatedTask)
            finish()
        }
    }
}