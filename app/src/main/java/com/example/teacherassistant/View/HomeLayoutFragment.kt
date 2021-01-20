package com.example.teacherassistant.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.teacherassistant.R
import kotlinx.android.synthetic.main.home_layout.*

class HomeLayoutFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonCourses.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeLayoutFragment_to_manageCoursesFragment)
        }
        buttonStudents.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeLayoutFragment_to_manageStudentsFragment)
        }
        buttonDailyReport.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeLayoutFragment_to_reportFragment)
        }
        buttonNotes.setOnClickListener {
            view.findNavController().navigate(R.id.action_homeLayoutFragment_to_notesFragment)
        }
    }



}