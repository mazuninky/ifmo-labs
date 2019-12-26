package ru.ifmo.course.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_about_me.*
import kotlinx.android.synthetic.main.fragment_about_me.view.*
import ru.ifmo.course.R

class AboutMeFragment : Fragment() {

    private lateinit var aboutMeViewModel: AboutMeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        aboutMeViewModel =
            ViewModelProviders.of(this).get(AboutMeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_about_me, container, false)

        root.backBtn.setOnClickListener {
            val nav = findNavController()
            nav.popBackStack()
        }
        return root
    }
}