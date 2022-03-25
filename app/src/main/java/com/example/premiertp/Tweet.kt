package com.example.premiertp
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tweet")
data class Tweet (
    @PrimaryKey(autoGenerate = true) val _id: Int = 0,
    val title: String,
    val description: String
)

