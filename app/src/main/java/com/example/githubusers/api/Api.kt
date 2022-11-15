package com.example.githubusers.api

import com.example.githubusers.data.model.DetailUserResponse
import com.example.githubusers.data.model.RepoUserResponse
import com.example.githubusers.data.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    // Search users endpoint
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    // Detail user endpoint
    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username : String
    ): Call<DetailUserResponse>

    // Repositories by user endpoint
    @GET("users/{username}/repos")
    @Headers("Authorization: token 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")
    fun getRepoByUser(
        @Path("username") username : String
    ): Call<ArrayList<RepoUserResponse>>
}