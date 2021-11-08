package com.example.kt_retrofit.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kt_retrofit.api.RetrofitInstance
import com.example.kt_retrofit.model.Post
import com.example.kt_retrofit.model.User
import kotlinx.coroutines.launch

const val TAG = "DetailViewModel"

class DetailViewModel : ViewModel() {

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post>
        get() = _post

    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    fun getPostDetails(postId: Int) {
        val api = RetrofitInstance.api
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val fetchedPosts = api.getPost(postId)
                val fetchedUsers = api.getUser(fetchedPosts.userId)
                _post.value = fetchedPosts
                _user.value = fetchedUsers
            } catch (e: Exception) {
                Log.e(TAG, "Exception $e")
            } finally {
                _isLoading.value = false
            }
        }
    }
}