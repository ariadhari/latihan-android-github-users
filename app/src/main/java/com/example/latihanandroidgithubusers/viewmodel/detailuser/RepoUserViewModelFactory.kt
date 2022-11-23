package com.example.latihanandroidgithubusers.viewmodel.detailuser

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.latihanandroidgithubusers.repository.RepoUserRepository

class RepoUserViewModelFactory(private val repoUserRepository: RepoUserRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RepoUserViewModel(repoUserRepository) as T
    }
}