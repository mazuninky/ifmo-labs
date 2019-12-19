package xyz.mazuninky.lab5.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class GalleryViewModel : ViewModel() {

    private val _images = MutableLiveData<List<Image>>().apply {
        value = emptyList()
    }

    init {
        _images.postValue( generateRandomImageUrl())
    }

    val images: LiveData<List<Image>> = _images
}

fun generateRandomImageUrl(): List<Image> {
    val list = mutableListOf<Image>()

    val count = Random.nextInt(50, 1000)
    for (i in 0 until count) {
        list.add(Image("https://picsum.photos/seed/${Random.nextInt(5000)}/500.jpg"))
    }

    return list
}