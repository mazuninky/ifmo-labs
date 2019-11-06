package xyz.mazuninky.lab3

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

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
    private val images: List<Image>,
    private val imageClickListener: () -> Unit
) :
    RecyclerView.Adapter<ImageRecyclerHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageRecyclerHolder {
        val imageView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false) as ImageView

        imageView.setOnClickListener { imageClickListener() }

        return ImageRecyclerHolder(imageView)
    }

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageRecyclerHolder, position: Int) {
        holder.loadImage(images[position].url)
    }
}