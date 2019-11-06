package xyz.mazuninky.lab3

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var player: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        player = MediaPlayer.create(this, R.raw.sound)

        recycler_view.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.adapter = ImageRecyclerAdapter(generateRandomImageUrl(), this::playSound)
    }

    private fun playSound() {
        player.start()
    }
}


fun generateRandomImageUrl(): List<Image> {
    val list = mutableListOf<Image>()

    val count = Random.nextInt(50, 1000)
    for (i in 0 until count) {
        list.add(Image("https://picsum.photos/seed/${Random.nextInt(5000)}/500.jpg"))
    }

    return list
}
