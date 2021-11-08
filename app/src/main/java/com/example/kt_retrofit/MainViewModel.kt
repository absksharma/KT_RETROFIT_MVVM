package com.example.kt_retrofit

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kt_retrofit.api.RetrofitInstance
import com.example.kt_retrofit.model.Post
import kotlinx.coroutines.launch

private const val TAG = "mainViewModel"

class MainViewModel : ViewModel() {
    /**
     * To show data in main UI
     */
    private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
    val posts: LiveData<List<Post>>
        get() = _posts

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    fun getPost() {
        viewModelScope.launch {
            _isLoading.value = true
            val fetchedPosts = RetrofitInstance.api.getPosts()
//            Log.i(TAG, "fetched posts $fetchedPosts")
            _posts.value = fetchedPosts
            _isLoading.value = false
        }
    }
}