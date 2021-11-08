package com.example.kt_retrofit.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.kt_retrofit.POST_ID
import com.example.kt_retrofit.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var viewModel: DetailViewModel
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val postId = intent.getIntExtra(POST_ID, -1)

        viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        viewModel.isLoading.observe(this, Observer { isLoading ->
            binding.detailProgressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.clContent.visibility = if (isLoading) View.GONE else View.VISIBLE
        })

        viewModel.user.observe(this, Observer { user ->
            binding.tvUserName.text = user.name
            binding.tvUsername.text = user.username
            binding.tvUserEmail.text = user.email
            binding.tvWebsite.text = user.website
        })

        viewModel.post.observe(this, Observer { post ->
            binding.tvPostId.text = "Post #${post.id}"
            binding.tvTitle.text = post.title
            binding.tvBody.text = post.body

        })

        viewModel.getPostDetails(postId)

    }
}