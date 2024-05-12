package com.example.myapptaskm.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String,
    val task: String
)