package com.example.latihanandroidgithubusers.viewmodel.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.latihanandroidgithubusers.api.RetrofitClient
import com.example.latihanandroidgithubusers.data.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class UserViewModel : ViewModel() {
    val listUser = MutableLiveData<ArrayList<User>>()

    fun setSearchUsers(query: String){
        // using coroutine
        GlobalScope.launch(Dispatchers.IO){
            val response = RetrofitClient.apiInstance.getSearchUsers(query)
            if (response.isSuccessful){
                listUser.postValue(response.body()?.items)
            }
        }
    }

    fun getSearchUsers(): LiveData<ArrayList<User>>{
        return listUser
    }
}