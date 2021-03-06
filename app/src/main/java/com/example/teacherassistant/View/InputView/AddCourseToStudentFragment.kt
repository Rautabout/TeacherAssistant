package com.example.teacherassistant.View.InputView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.Adapters.CourseToStudentAdapter
import com.example.teacherassistant.ViewModel.CourseStudentViewModel
import com.example.teacherassistant.ViewModel.CourseViewModel
import com.example.teacherassistant.ViewModel.StudentViewModel
import kotlinx.android.synthetic.main.merge_layout.*

class AddCourseToStudentFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var myLayoutManager : RecyclerView.LayoutManager
    private lateinit var myAdapter : CourseToStudentAdapter
    private lateinit var courseStudentViewModel : CourseStudentViewModel
    private lateinit var studentViewModel : StudentViewModel
    private lateinit var courseViewModel : CourseViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        studentViewModel = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        courseViewModel = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)
        courseStudentViewModel = ViewModelProvider(requireActivity()).get(CourseStudentViewModel::class.java)
        courseStudentViewModel.setCoursesNotInStudent(studentViewModel.currentStudent!!)

        myAdapter = CourseToStudentAdapter(courseStudentViewModel.coursesNotInStudent!!)
        courseStudentViewModel.coursesNotInStudent!!.observe(viewLifecycleOwner, { myAdapter.notifyDataSetChanged() })

        myLayoutManager = LinearLayoutManager(context)

        return inflater.inflate(R.layout.merge_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = mergeRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        mergeOptionTV.text=""
        mergeOptionSpecificTV.text="Courses"
        mergeAddButton.text="Save"
        mergeAddButton.setOnClickListener {
            myAdapter.coursesChecked.forEach{
                course -> courseStudentViewModel.addCourseStudentConnection(course,studentViewModel.currentStudent!!)
            }
            activity?.onBackPressed()
        }
    }

}