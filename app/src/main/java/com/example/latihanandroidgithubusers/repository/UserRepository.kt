package com.example.latihanandroidgithubusers.repository

import com.example.latihanandroidgithubusers.api.RetrofitClient
import com.example.latihanandroidgithubusers.data.model.UserResponse
import retrofit2.Response


class UserRepository {
    suspend fun setSearchUsers(query: String): Response<UserResponse> = RetrofitClient.apiInstance.getSearchUsers(query)
}