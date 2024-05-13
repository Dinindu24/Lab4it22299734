package com.example.myapptaskm.repository

import androidx.lifecycle.LiveData
import com.example.myapptaskm.dao.TaskDao
import com.example.myapptaskm.model.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: LiveData<List<Task>> =  taskDao.getAllTasks()
    //val allTask: LiveData<List<Task>> =  taskDao.getAllTasks()


    suspend fun insert(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.insert(task)
        }
    }

    suspend fun update(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.update(task)
        }
    }

    suspend fun delete(task: Task) {
        withContext(Dispatchers.IO) {
            taskDao.delete(task)
        }
    }

    fun getTask(taskId: Int): LiveData<Task> {
        return taskDao.getTask(taskId)
    }


}