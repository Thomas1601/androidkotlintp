package com.example.premiertp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tweet::class], version = 1)
abstract class TweetDatabase : RoomDatabase() {
    abstract fun tweetDao(): TweetDao

    companion object {
        @Volatile
        private var INSTANCE: TweetDatabase? = null
        fun getInstance(context: Context): TweetDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    TweetDatabase::class.java, "tweet_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }

        }

    }
}