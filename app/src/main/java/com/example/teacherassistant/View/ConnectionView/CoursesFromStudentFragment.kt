package com.example.teacherassistant.View.ConnectionView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.Adapters.CoursesFromStudentAdapter
import com.example.teacherassistant.ViewModel.Adapters.StudentsFromCourseAdapter
import com.example.teacherassistant.ViewModel.CourseStudentViewModel
import com.example.teacherassistant.ViewModel.CourseViewModel
import com.example.teacherassistant.ViewModel.StudentViewModel
import kotlinx.android.synthetic.main.merge_layout.*


class CoursesFromStudentFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var myLayoutManager : RecyclerView.LayoutManager
    private lateinit var myAdapter : CoursesFromStudentAdapter
    private lateinit var studentViewModel : StudentViewModel
    private lateinit var courseViewModel : CourseViewModel
    private lateinit var courseStudentViewModel : CourseStudentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myLayoutManager = LinearLayoutManager(context)
        studentViewModel = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        courseViewModel = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)
        courseStudentViewModel = ViewModelProvider(requireActivity()).get(CourseStudentViewModel::class.java)
        courseStudentViewModel.setCoursesFromStudent(studentViewModel.currentStudent)

        myAdapter = CoursesFromStudentAdapter(courseStudentViewModel.coursesFromStudent!!,
            { course -> courseStudentViewModel.deleteStudentCourseConnection(course,studentViewModel.currentStudent!!) },
            { course -> courseViewModel.setCurrentCourse(course); courseStudentViewModel.setCurrentCourseStudent( course,studentViewModel.currentStudent!!) } )
        courseStudentViewModel.coursesFromStudent!!.observe(
            viewLifecycleOwner, { myAdapter.notifyDataSetChanged() })
        courseStudentViewModel.connections.observe(viewLifecycleOwner, { myAdapter.notifyDataSetChanged() })
        courseViewModel.setCurrentCourse(null)

        return inflater.inflate(R.layout.merge_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = mergeRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        mergeOptionTV.text="Student: "
        mergeOptionSpecificTV.text=studentViewModel.currentStudent?.firstName.toString() + " "+ studentViewModel.currentStudent?.lastName.toString()
        mergeAddButton.text="Add Course"

        mergeAddButton.setOnClickListener { view.findNavController().navigate(
            R.id.action_coursesFromStudentFragment_to_addCourseToStudentFragment2)
        }
    }

}