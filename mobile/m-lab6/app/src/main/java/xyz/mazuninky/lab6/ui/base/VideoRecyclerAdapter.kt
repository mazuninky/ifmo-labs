package xyz.mazuninky.lab6.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import xyz.mazuninky.lab6.R
import xyz.mazuninky.lab6.domain.Video
import xyz.mazuninky.lab6.ui.trends.TrendsFragmentDirections

class VideoRecyclerHolder(
    private val image: ImageView,
    private val onClickListener: (Video) -> Unit
) : RecyclerView.ViewHolder(image) {
    private lateinit var _video: Video

    init {
        image.setOnClickListener {
            if (::_video.isInitialized) {
                onClickListener(_video)
            }
        }
    }

    fun loadVideo(video: Video) {
        Glide.with(image.context)
            .load(video.img)
            .error(R.drawable.error_map)
            .placeholder(R.drawable.load_map)
            .into(image)

        _video = video
    }
}

class VideoRecyclerAdapter(
    private val clickListener: (Video) -> Unit = {},
    private var images: List<Video> = emptyList()
) :
    RecyclerView.Adapter<VideoRecyclerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoRecyclerHolder {
        val imageView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false) as ImageView

        return VideoRecyclerHolder(imageView, clickListener)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: VideoRecyclerHolder, position: Int) {
        holder.loadVideo(images[position])
    }

    fun setImages(images: List<Video>) {
        this.images = images
        notifyDataSetChanged()
    }
}