package com.example.latihanandroidgithubusers.view.ui.detailuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.example.latihanandroidgithubusers.databinding.ActivityDetailUserBinding
import com.example.latihanandroidgithubusers.repository.DetailUserRepository
import com.example.latihanandroidgithubusers.repository.RepoUserRepository
import com.example.latihanandroidgithubusers.view.adapter.detailuser.DetailUserAdapter
import com.example.latihanandroidgithubusers.viewmodel.detailuser.DetailUserViewModel
import com.example.latihanandroidgithubusers.viewmodel.detailuser.DetailUserViewModelFactory
import com.example.latihanandroidgithubusers.viewmodel.detailuser.RepoUserViewModel
import com.example.latihanandroidgithubusers.viewmodel.detailuser.RepoUserViewModelFactory

class DetailUserActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    private lateinit var viewModelRepo: RepoUserViewModel
    private lateinit var adapter: DetailUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val detailUserRepository = DetailUserRepository()
        val repoUserRepository = RepoUserRepository()
        val viewModelFactory = DetailUserViewModelFactory(detailUserRepository)
        val viewModelRepoFactory = RepoUserViewModelFactory(repoUserRepository)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        if (username != null) {
            Log.d("Failure", username)
        }

        // inisiasi detail user adapter
        adapter = DetailUserAdapter()

        viewModel = ViewModelProvider(this, viewModelFactory)[DetailUserViewModel::class.java]

        viewModel.setUserDetail(username.toString())
        viewModel.getUserDetail().observe(this) {
            if (it != null) {
                binding.apply {
                    tvFullname.text = it.name
                    tvUsername.text = "@" + it.login
                    tvBio.text = it.bio
                    Glide.with(this@DetailUserActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .centerCrop()
                        .apply(RequestOptions.circleCropTransform())
                        .into(ivProfile)
                }
            }
        }

        viewModelRepo = ViewModelProvider(this, viewModelRepoFactory)[RepoUserViewModel::class.java]

        viewModelRepo.setRepoUser(username.toString())
        showLoading(true)
        viewModelRepo.getRepoByUser().observe(this@DetailUserActivity) {
            if (it != null) {
                binding.apply {
                    rvRepo.layoutManager = LinearLayoutManager(this@DetailUserActivity)
                    rvRepo.setHasFixedSize(true)
                    rvRepo.addItemDecoration(DividerItemDecoration(baseContext, DividerItemDecoration.VERTICAL))
                    rvRepo.adapter = adapter

                    adapter.setList(it)
                    showLoading(false)
                }
            }
        }

    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}