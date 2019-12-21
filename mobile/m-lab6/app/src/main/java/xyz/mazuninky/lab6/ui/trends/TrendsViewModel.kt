package xyz.mazuninky.lab6.ui.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import xyz.mazuninky.lab6.domain.Video
import kotlin.random.Random

class GalleryViewModel : ViewModel() {

    private val _images = MutableLiveData<List<Video>>().apply {
        value = emptyList()
    }

//    init {
//        _images.postValue( )
//    }

    val images: LiveData<List<Video>> = _images
}