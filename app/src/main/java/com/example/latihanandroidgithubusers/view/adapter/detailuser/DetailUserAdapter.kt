package com.example.latihanandroidgithubusers.view.adapter.detailuser

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.latihanandroidgithubusers.data.model.RepoUserResponse
import com.example.latihanandroidgithubusers.databinding.ItemRepoBinding
import com.example.latihanandroidgithubusers.helper.*

class DetailUserAdapter : RecyclerView.Adapter<DetailUserAdapter.RepoViewHolder>() {

    private val list = ArrayList<RepoUserResponse>()

    // untuk dipanggil di viewModel
    fun setList(repos: ArrayList<RepoUserResponse>){
        list.clear() // list repos dibersihkan terlebih dahulu
        list.addAll(repos) // list repos akan diisi ulang dengan data yang baru
        notifyDataSetChanged() // memberitahu adapter bahwa data berubah
    }

    inner class RepoViewHolder (val binding: ItemRepoBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(repo: RepoUserResponse){
            binding.apply {
                tvName.text = repo.name
                tvDescription.text = repo.description
                tvStar.text = repo.stargazers_count.toString()
                tvUpdated.text = DateFormatLocale.covertToTimeDiff(repo.updated_at)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoViewHolder {
        val view = ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RepoViewHolder((view))
    }

    override fun onBindViewHolder(holder: RepoViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

}