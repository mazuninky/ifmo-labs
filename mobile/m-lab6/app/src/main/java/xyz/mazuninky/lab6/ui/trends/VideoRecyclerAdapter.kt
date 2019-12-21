package xyz.mazuninky.lab6.ui.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import xyz.mazuninky.lab6.R
import xyz.mazuninky.lab6.domain.Video

class ImageRecyclerHolder(private val image: ImageView) : RecyclerView.ViewHolder(image) {

    fun loadImage(url: String) {
        Glide.with(image.context)
            .load(url)
            .error(R.drawable.error_map)
            .placeholder(R.drawable.load_map)
            .into(image)
    }
}

class ImageRecyclerAdapter(
    private var images: List<Video> = emptyList()
) :
    RecyclerView.Adapter<ImageRecyclerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageRecyclerHolder {
        val imageView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false) as ImageView

        return ImageRecyclerHolder(imageView)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageRecyclerHolder, position: Int) {
        holder.loadImage(images[position].url)
    }

    fun setImages(images: List<Video>) {
        this.images = images
        notifyDataSetChanged()
    }
}