package xyz.mazuninky.lab6.ui.playlist.jetpack

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import xyz.mazuninky.lab6.domain.Video
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import kotlinx.coroutines.*
import xyz.mazuninky.lab6.network.YoutubeApi
import xyz.mazuninky.lab6.network.mapToEntity
import xyz.mazuninky.lab6.network.mapToEntityWithResourceId

class JetpackViewModel : ViewModel() {

    private val _videos = MutableLiveData<List<Video>>().apply {
        value = emptyList()
    }

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/youtube/v3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val youtubeAPI = retrofit.create(YoutubeApi::class.java)

        coroutineScope.launch {
            val videoData = youtubeAPI.getPlaylist("PLWz5rJ2EKKc9mxIBd0DRw9gwXuQshgmn2").mapToEntityWithResourceId()

            _videos.postValue(videoData)
        }

    }

    override fun onCleared() {
        coroutineScope.cancel()
    }

    val images: LiveData<List<Video>> = _videos
}