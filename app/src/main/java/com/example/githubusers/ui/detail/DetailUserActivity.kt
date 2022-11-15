package com.example.githubusers.ui.detail

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
import com.example.githubusers.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel
    private lateinit var adapter: DetailUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val username = intent.getStringExtra(EXTRA_USERNAME)
        if (username != null) {
            Log.d("Failure", username)
        }

        // inisiasi detail user adapter
        adapter = DetailUserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)

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

        viewModel.setRepoUser(username.toString())
        showLoading(true)
        viewModel.getRepoByUser().observe(this@DetailUserActivity) {
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

//    fun timeAgo(time: Long): String? {
//        val mTime = timestampToMilli(time)
//        val currentTime = System.currentTimeMillis()
//
//        if (mTime > currentTime || mTime <= 0) return null
//        val time_elapsed = currentTime - mTime
//        val thisDay: Int = SimpleDateFormat("dd", Locale.US).format(currentTime).toInt()
//        val thisMonth: Int = SimpleDateFormat("MM", Locale.US).format(currentTime).toInt()
//        val thisYear: Int = SimpleDateFormat("yyy", Locale.US).format(currentTime).toInt()
//
//        val agoDay: Int = SimpleDateFormat("dd", Locale.US).format(mTime).toInt()
//        val agoMonth: Int = SimpleDateFormat("MM", Locale.US).format(mTime).toInt()
//        val agoYear: Int = SimpleDateFormat("yyy", Locale.US).format(mTime).toInt()
//
//        val seconds = time_elapsed
//        val minutes = Math.round((time_elapsed / 60000).toDouble()).toInt()
//        val hours = Math.round((time_elapsed / 3600000).toDouble()).toInt()
//        val days = Math.round((time_elapsed / 86400000).toDouble()).toInt()
//        val weeks = Math.round((time_elapsed / 604800000).toDouble()).toInt()
//        val months = Math.round((time_elapsed / 2600640000).toDouble()).toInt()
//        val years = Math.round((time_elapsed / 31207680000).toDouble()).toInt()
//
//        //Yesterday
//         if ( (thisYear - agoYear) >= 1 ) {
//             return SimpleDateFormat("MMM dd, yyyy - hh:ma", Locale.US).format(mTime)
//         } else if ( (thisMonth - agoMonth) >= 1 ) {
//             return SimpleDateFormat("MMM dd", Locale.US).format(mTime)
//         } else if ( thisMonth == agoMonth && (thisDay - agoDay) == 1 ) {
//             return "Yesterday"
//         }
//        // Seconds
//        else if ( seconds <= 60 ) {
//            return "just now"
//        }
//        //Minutes
//        else if ( minutes <= 60 ) {
//            return if (minutes == 1) {
//                "one minute ago"
//            } else {
//                "$minutes minutes ago"
//            }
//        }
//        //Hours
//        else if ( hours <= 24 ) {
//            return if ( hours == 1 ) {
//                "an hour and " + ( minutes - 60 ) + " minutes ago"
//            } else {
//                "$hours hrs ago"
//            }
//        }
//        //Days
//        else if ( days <= 7 ) {
//            return if (days == 1) {
//                "$days day and " + (hours - 24) + " hrs ago"
//            } else {
//                "$days days ago"
//            }
//        }
//        //Weeks
//        else if ( weeks <= 4.3 ) {
//            return if (weeks == 1) {
//                "a week ago"
//            } else {
//                "$weeks weeks ago"
//            }
//        }
//        return null
//    }

}