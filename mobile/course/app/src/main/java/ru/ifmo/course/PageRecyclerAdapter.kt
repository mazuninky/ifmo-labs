package ru.ifmo.course

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_carousel.view.*

class VideoRecyclerHolder(
    private val card: CardView,
    private val onClickListener: (PageItem) -> Unit
) : RecyclerView.ViewHolder(card) {
    private lateinit var _video: PageItem

    init {
        card.setOnClickListener {
            if (::_video.isInitialized) {
                onClickListener(_video)
            }
        }
    }

    fun loadVideo(video: PageItem) {
        Glide.with(card.context)
            .load(video.image)
//            .load(R.drawable.hobby)
//            .error(R.drawable.error_map)
//            .placeholder(R.drawable.load_map)
            .into(card.imageViewPiece)

        card.textViewPieceName.text = video.text
        _video = video
    }
}

class PageRecyclerAdapter(
    private val clickListener: (PageItem) -> Unit = {},
    private var images: List<PageItem> = emptyList()
) :
    RecyclerView.Adapter<VideoRecyclerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoRecyclerHolder {
        val imageView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_carousel, parent, false) as CardView

        return VideoRecyclerHolder(imageView, clickListener)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: VideoRecyclerHolder, position: Int) {
        holder.loadVideo(images[position])
    }

    fun setImages(images: List<PageItem>) {
        this.images = images
        notifyDataSetChanged()
    }
}