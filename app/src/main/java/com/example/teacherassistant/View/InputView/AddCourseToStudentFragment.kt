package com.example.teacherassistant.View.InputView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.CourseStudentViewModel
import com.example.teacherassistant.ViewModel.CourseViewModel
import com.example.teacherassistant.ViewModel.StudentViewModel

class AddCourseToStudentFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var myLayoutManager: RecyclerView.LayoutManager

    private lateinit var courseStudentViewModel: CourseStudentViewModel
    private lateinit var studentViewModel: StudentViewModel
    private lateinit var courseViewModel: CourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myLayoutManager=LinearLayoutManager(context)
        courseViewModel= ViewModelProvider(requireActivity()).get(courseViewModel::class.java)
        studentViewModel=ViewModelProvider(requireActivity()).get(studentViewModel::class.java)
        courseStudentViewModel=ViewModelProvider(requireActivity()).get(courseStudentViewModel::class.java)
        courseStudentViewModel.setCoursesNotInStudent(studentViewModel.currentStudent!!)


        return inflater.inflate(R.layout.merge_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}