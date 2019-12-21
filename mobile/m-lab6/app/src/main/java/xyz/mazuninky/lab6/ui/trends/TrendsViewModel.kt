package xyz.mazuninky.lab6.ui.trends

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import xyz.mazuninky.lab6.domain.Video
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import xyz.mazuninky.lab6.network.YoutubeApi
import xyz.mazuninky.lab6.network.mapToEntity

val coroutineScope = CoroutineScope(Dispatchers.IO)

class TrendsViewModel : ViewModel() {

    private val _videos = MutableLiveData<List<Video>>().apply {
        value = emptyList()
    }

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.googleapis.com/youtube/v3")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val youtubeAPI = retrofit.create(YoutubeApi::class.java)

        coroutineScope.launch {
            val videoData = youtubeAPI.findTrends().mapToEntity()

            _videos.postValue(videoData)
        }

    }

    val images: LiveData<List<Video>> = _videos
}