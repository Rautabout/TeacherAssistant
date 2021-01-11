package com.example.teacherassistant.View

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
import com.example.teacherassistant.ViewModel.Adapters.GradeAdapter
import com.example.teacherassistant.ViewModel.CourseStudentViewModel
import com.example.teacherassistant.ViewModel.CourseViewModel
import com.example.teacherassistant.ViewModel.GradeViewModel
import com.example.teacherassistant.ViewModel.StudentViewModel
import kotlinx.android.synthetic.main.add_grade_layout.*
import kotlinx.android.synthetic.main.manage_grade_layout.*

class ManageGradeFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var myLayoutManager : RecyclerView.LayoutManager
    private lateinit var myAdapter : GradeAdapter
    private lateinit var studentViewModel : StudentViewModel
    private lateinit var courseViewModel : CourseViewModel
    private lateinit var courseStudentViewModel : CourseStudentViewModel
    private lateinit var gradeViewModel : GradeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myLayoutManager = LinearLayoutManager(context)
        studentViewModel = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        courseViewModel = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)
        courseStudentViewModel = ViewModelProvider(requireActivity()).get(CourseStudentViewModel::class.java)
        gradeViewModel=ViewModelProvider(requireActivity()).get(GradeViewModel::class.java)

        gradeViewModel.setGradesFromCurrentStudent(courseStudentViewModel.currentCourseStudent)

        myAdapter = GradeAdapter(gradeViewModel.gradesFromCurrentCourseStudent!!,
                {grade -> gradeViewModel.deleteGrade(grade)},
                {grade -> gradeViewModel.setCurrentGrade(grade)})
        gradeViewModel.gradesFromCurrentCourseStudent!!.observe(viewLifecycleOwner, {myAdapter.notifyDataSetChanged()})
        gradeViewModel.setCurrentGrade(null)

        return inflater.inflate(R.layout.manage_grade_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = gradeRecyclerView.apply {
            this.layoutManager=myLayoutManager
            this.adapter=myAdapter
        }

        courseGradeTV.text="Course: "+courseViewModel.currentCourse!!.courseName
        studentGradeTV.text="Student: "+studentViewModel.currentStudent!!.firstName+" "+studentViewModel.currentStudent!!.lastName

        buttonAddGrade.setOnClickListener {
            view.findNavController().navigate(R.id.action_manageGradeFragment_to_addEditGradeFragment)
        }
    }
}