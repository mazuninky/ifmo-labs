package ru.ifmo.course.ui.learn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_diplom.view.*
import kotlinx.android.synthetic.main.fragment_diplom.view.backBtn
import kotlinx.android.synthetic.main.fragment_learn.view.*
import ru.ifmo.course.R
import ru.ifmo.course.ui.learn.MainStepperAdapter

class LearnFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_learn, container, false)
        root.backBtn.setOnClickListener {
            val nav = findNavController()
            nav.popBackStack()
        }

        root.verticalStepper.setStepperAdapter(MainStepperAdapter(context!!))
        return root
    }
}