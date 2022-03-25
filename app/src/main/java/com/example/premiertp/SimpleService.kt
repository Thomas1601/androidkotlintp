package com.example.premiertp

import com.squareup.moshi.JsonClass
import retrofit2.http.*

interface SimpleService {


    @GET("posts")
    suspend fun listPosts(): List<Post>

    @GET("posts/{userId}")
    suspend fun listByUser(@Path("userId") userId:String): List<Post>

    @GET("posts/search")  // becomes post/search?filter=query
    suspend fun search(@Query("filter") search: String): List<Post>

    @POST("posts/new")
    suspend fun create(@Body post : Post): Post
}

@JsonClass(generateAdapter = true)
data class Post (
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?)
