package com.example.kt_retrofit

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kt_retrofit.databinding.ActivityMainBinding
import com.example.kt_retrofit.detail.DetailActivity
import com.example.kt_retrofit.model.Post

const val POST_ID = "POST_ID"

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    private lateinit var blogPostAdapter: BlogPostAdapter
    private val blogPosts = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.posts.observe(this, Observer { posts ->
            Log.i("MainViewModel", "${listOf(posts).size}")
            blogPosts.addAll(posts)
            blogPostAdapter.notifyDataSetChanged()
        })
        viewModel.isLoading.observe(this, Observer { isLoading ->
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        })

        blogPostAdapter =
            BlogPostAdapter(this, blogPosts, object : BlogPostAdapter.ItemClickListener {
                override fun onItemClick(post: Post) {

                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
                    intent.putExtra(POST_ID, post.id)
                    startActivity(intent)
                }
            })

        binding.rvPosts.apply {
            adapter = blogPostAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        binding.button.setOnClickListener {
            viewModel.getPost()
        }
    }
}