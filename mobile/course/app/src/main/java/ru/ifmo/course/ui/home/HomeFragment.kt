package ru.ifmo.course.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main_tes.*
import kotlinx.android.synthetic.main.activity_main_tes.view.*
import ru.ifmo.course.PageItem
import ru.ifmo.course.PageRecyclerAdapter
import ru.ifmo.course.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.activity_main_tes, container, false)
        root.mainRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = PageRecyclerAdapter({
            val nav = findNavController()
            nav.navigate(it.navigate)
        })
        adapter.setImages(
            listOf(
                PageItem(
                    "https://img.freepik.com/free-vector/illustration-passion_53876-17884.jpg?size=626&ext=jpg",
                    "Обо мне",
                    R.id.action_navigation_home_to_navigation_about_me
                ), PageItem(
                    "https://www.u-b-s.ru/wp-content/uploads/2015/04/obuchenie.jpg",
                    "Обучение",
                    R.id.action_navigation_home_to_navigation_learn
                ),
                PageItem(
                    "https://kurgan.ru/media/post/9144/197f89583db9cd092686e07c2c479603.jpg",
                    "Диплом",
                    R.id.action_navigation_home_to_navigation_diplom
                ),
                PageItem(
                    "https://i.work.ua/article/1051b.jpg",
                    "Планы",
                    R.id.action_navigation_home_to_navigation_goals
                )
            )
        )
        root.mainRecyclerView.adapter = adapter
        root.sendMail.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://vk.com/mazuninky")
            
            startActivity(intent)
        }
        return root
    }
}

//fun openWebPage() {
//    val intent = Intent(Intent.ACTION_VIEW)
//    intent.data = Uri.parse("http://www.ifmo.ru/ru/")
//
//    if (intentIsSafe(intent))
//        startActivity(intent)
//    else {
//        Toast.makeText(this, "Your phone have no app can open web page", Toast.LENGTH_LONG)
//            .show()
//    }
//}