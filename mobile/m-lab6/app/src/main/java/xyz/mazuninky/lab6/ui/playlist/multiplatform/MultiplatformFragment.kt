package xyz.mazuninky.lab6.ui.playlist.multiplatform

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import xyz.mazuninky.lab6.R
import xyz.mazuninky.lab6.ui.base.VideoRecyclerAdapter

class MultiplatformFragment : Fragment() {

    private lateinit var trendsViewModel: MultiplatformViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        trendsViewModel = ViewModelProviders.of(this).get(MultiplatformViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        root.gallery_recycler.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val adapter = VideoRecyclerAdapter({
            val action = MultiplatformFragmentDirections.actionNavMultiplatformToNavVideo(it.id)
            findNavController().navigate(action)
        })

        root.gallery_recycler.adapter = adapter

        trendsViewModel.images.observe(this, Observer {
            adapter.setImages(it)
        })
        return root
    }
}