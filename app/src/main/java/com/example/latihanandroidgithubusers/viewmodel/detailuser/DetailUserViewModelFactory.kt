package com.example.latihanandroidgithubusers.viewmodel.detailuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.latihanandroidgithubusers.repository.DetailUserRepository

class DetailUserViewModelFactory(private val detailUserRepository: DetailUserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DetailUserViewModel(detailUserRepository) as T
    }
}