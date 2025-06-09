package com.example.todoapp

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date
import java.time.Instant

@Entity
data class list(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name:String,
    val createdOn: java.util.Date
)


