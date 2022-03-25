package com.example.premiertp

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TweetDao {

    @Insert
    suspend fun insert(vararg tweet: Tweet)

    @Update
    suspend fun update(tweet: Tweet)

    @Delete
    suspend fun delete(tweet: Tweet)

    @Query("SELECT * FROM tweet")
    suspend fun getAll(): Array<Tweet>

    @Query("SELECT * FROM tweet WHERE title = :name")
    suspend fun getTweetByTitle(name: String): LiveData<Tweet>

    @Query("SELECT * FROM colors WHERE _id = :id")
    suspend fun getColorById(id: String): LiveData<Tweet>
}