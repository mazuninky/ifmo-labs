package xyz.mazuninky.lab6.ui.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import xyz.mazuninky.lab6.R

class GalleryFragment : Fragment() {

    private lateinit var galleryViewModel: VideoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        galleryViewModel =
            ViewModelProviders.of(this).get(VideoViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_video_player, container, false)
        return root
    }
}