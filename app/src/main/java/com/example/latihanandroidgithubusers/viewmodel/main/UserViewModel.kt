package com.example.latihanandroidgithubusers.viewmodel.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.latihanandroidgithubusers.data.model.User
import com.example.latihanandroidgithubusers.repository.UserRepository
import kotlinx.coroutines.launch


class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    private val listUser = MutableLiveData<ArrayList<User>>()

    fun setSearchUsers(query: String){
        // using coroutine
        viewModelScope.launch {
            try {
                val response = userRepository.setSearchUsers(query)
                if (response.isSuccessful){
                    listUser.postValue(response.body()?.items)
                }
            }
            catch (e:Exception){
                Log.d("main", "setSearchUsers: ${e.message}")
            }
        }
    }

    fun getSearchUsers(): LiveData<ArrayList<User>>{
        return listUser
    }
}