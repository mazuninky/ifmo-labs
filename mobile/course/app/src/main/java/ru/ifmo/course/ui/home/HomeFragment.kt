package ru.ifmo.course.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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
//        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
        root.mainRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val adapter = PageRecyclerAdapter({
            val nav = findNavController()
            nav.navigate(it.navigate)
        })
        adapter.setImages(
            listOf(
                PageItem("https://img.freepik.com/free-vector/illustration-passion_53876-17884.jpg?size=626&ext=jpg", "Обо мне", R.id.action_navigation_home_to_navigation_about_me)
            )
        )
        root.mainRecyclerView.adapter = adapter
        return root
    }
}