package com.example.myapptaskm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myapptaskm.CharSequenceConverter
import com.example.myapptaskm.dao.TaskDao
import com.example.myapptaskm.model.Task

@Database(entities = [Task::class], version = 1)
@TypeConverters(CharSequenceConverter::class)
abstract class TaskDatabase : RoomDatabase() {
    // Database operations DAOs



    companion object {
        @Volatile
        private var INSTANCE: TaskDatabase? = null

        fun getDatabase(context: Context): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
    abstract fun taskDao(): TaskDao
}