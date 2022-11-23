package com.example.latihanandroidgithubusers.repository

import com.example.latihanandroidgithubusers.api.RetrofitClient
import com.example.latihanandroidgithubusers.data.model.DetailUserResponse
import retrofit2.Response

class DetailUserRepository {
    suspend fun setUserDetail(username: String): Response<DetailUserResponse> = RetrofitClient.apiInstance.getDetailUser(username)
}