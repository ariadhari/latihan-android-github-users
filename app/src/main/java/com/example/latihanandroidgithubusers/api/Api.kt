package com.example.latihanandroidgithubusers.api

import com.example.latihanandroidgithubusers.data.model.DetailUserResponse
import com.example.latihanandroidgithubusers.data.model.RepoUserResponse
import com.example.latihanandroidgithubusers.data.model.UserResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    // Search users endpoint
    @GET("search/users")
    suspend fun getSearchUsers( // add suspend for coroutines
        @Query("q") query: String
    ): Response<UserResponse> // using coroutines

    // Detail user endpoint
    @GET("users/{username}")
    suspend fun getDetailUser( // add suspend for coroutines
        @Path("username") username : String
    ): Response<DetailUserResponse> // using coroutines

    // Repositories by user endpoint
    @GET("users/{username}/repos")
    suspend fun getRepoByUser( // add suspend for coroutines
        @Path("username") username : String
    ): Response<ArrayList<RepoUserResponse>> // using coroutines

}