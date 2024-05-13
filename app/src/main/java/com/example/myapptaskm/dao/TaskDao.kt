package com.example.myapptaskm.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapptaskm.model.Task
import com.example.myapptaskm.repository.TaskRepository
import android.util.Log

//annotation class Dao

@Dao
interface TaskDao {

    @Query("SELECT * FROM task")
    fun getAllTasks(): LiveData<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Delete
    fun delete(task: Task)

    @Query("SELECT * FROM task WHERE id = :taskId")
    fun getTask(taskId: Int): LiveData<Task>
}

