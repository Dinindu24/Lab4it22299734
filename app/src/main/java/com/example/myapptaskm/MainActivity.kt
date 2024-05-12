package com.example.myapptaskm

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapptaskm.database.TaskDatabase
import com.example.myapptaskm.model.Task
import com.example.myapptaskm.repository.TaskRepository
import com.example.myapptaskm.ui.theme.MyAppTaskmTheme
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize your repository
        val taskDao = TaskDatabase.getDatabase(application).taskDao()
        val repository = TaskRepository(taskDao)

        // Use TaskViewModelFactory to create your ViewModel
        val factory = TaskViewModelFactory(repository)
        taskViewModel = ViewModelProvider(this, factory).get(TaskViewModel::class.java)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = TaskAdapter()
        recyclerView.adapter = adapter

        taskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        taskViewModel.allTasks.observe(this, Observer { tasks: List<Task>? ->
            // Update the cached copy of the tasks in the adapter.
            tasks?.let { adapter.addTasks(it) }
        })

        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            // Navigate to NewTaskActivity when FAB is clicked.
            val intent = Intent(this@MainActivity, NewTaskActivity::class.java)
            startActivity(intent)
        }
    }
}

private fun Any.observe(mainActivity: MainActivity, observer: Observer<List<Task>?>) {

}
