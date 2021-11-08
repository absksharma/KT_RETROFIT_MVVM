package com.example.kt_retrofit.api

import com.example.kt_retrofit.model.Post
import com.example.kt_retrofit.model.User
import retrofit2.http.GET
import retrofit2.http.Path

interface BlogApi {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{id}")
    suspend fun getPost(@Path("id") postId: Int): Post

    @GET("users/{id}")
    suspend fun getUser(@Path("id") userId: Int): User
}