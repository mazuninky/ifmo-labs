package xyz.mazuninky.lab5.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GalleryViewModel : ViewModel() {

    private val _images = MutableLiveData<List<Video>>().apply {
        value = emptyList()
    }

    init {
        _images.postValue( generateRandomImageUrl())
    }

    val images: LiveData<List<Video>> = _images
}

fun generateRandomImageUrl(): List<Video> {
    val list = mutableListOf<Video>()

    val count = Random.nextInt(50, 1000)
    for (i in 0 until count) {
        list.add(Video("https://picsum.photos/seed/${Random.nextInt(5000)}/500.jpg"))
    }

    return list
}