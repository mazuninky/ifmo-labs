package xyz.mazuninky.lab6.ui.trends

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_gallery.view.*
import xyz.mazuninky.lab6.R

class TrendsFragment : Fragment() {

    private lateinit var trendsViewModel: TrendsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        trendsViewModel =
            ViewModelProviders.of(this).get(TrendsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        root.gallery_recycler.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        val adapter = ImageRecyclerAdapter()

        root.gallery_recycler.adapter = adapter

        trendsViewModel.images.observe(this, Observer {
            adapter.setImages(it)
        })
        return root
    }
}