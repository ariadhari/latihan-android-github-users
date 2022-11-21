package com.example.latihanandroidgithubusers.viewmodel.detailuser

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latihanandroidgithubusers.api.RetrofitClient
import com.example.latihanandroidgithubusers.data.model.DetailUserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class DetailUserViewModel: ViewModel() {
    val user = MutableLiveData<DetailUserResponse>()

    fun setUserDetail(username: String){
        // using coroutine
        GlobalScope.launch(Dispatchers.IO){
            val response = RetrofitClient.apiInstance.getDetailUser(username)
            if (response.isSuccessful){
                user.postValue(response.body())
            }
        }
    }

    fun getUserDetail(): LiveData<DetailUserResponse>{
        return user
    }

}