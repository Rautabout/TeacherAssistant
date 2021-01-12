package com.example.teacherassistant.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.Adapters.ReportAdapter
import com.example.teacherassistant.ViewModel.CourseStudentViewModel
import com.example.teacherassistant.ViewModel.CourseViewModel
import com.example.teacherassistant.ViewModel.GradeViewModel
import com.example.teacherassistant.ViewModel.StudentViewModel
import kotlinx.android.synthetic.main.report_layout.*
import java.text.SimpleDateFormat
import java.util.*

class ReportFragment : Fragment(){
    private lateinit var recyclerView : RecyclerView
    private lateinit var myLayoutManager : RecyclerView.LayoutManager
    private lateinit var myAdapter : ReportAdapter
    private lateinit var studentViewModel : StudentViewModel
    private lateinit var courseViewModel : CourseViewModel
    private lateinit var gradeViewModel : GradeViewModel
    private lateinit var courseStudentViewModel : CourseStudentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        myLayoutManager = LinearLayoutManager(context)
        studentViewModel = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        courseViewModel = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)
        gradeViewModel = ViewModelProvider(requireActivity()).get(GradeViewModel::class.java)
        courseStudentViewModel = ViewModelProvider(requireActivity()).get(CourseStudentViewModel::class.java)

        studentViewModel.students.observe(viewLifecycleOwner){}
        courseViewModel.courses.observe(viewLifecycleOwner){}
        courseStudentViewModel.connections.observe(viewLifecycleOwner){}

        myAdapter= ReportAdapter(gradeViewModel.gradesFromToday,courseStudentViewModel,studentViewModel,courseViewModel)
        gradeViewModel.gradesFromToday.observe(viewLifecycleOwner, {myAdapter.notifyDataSetChanged()})

        return inflater.inflate(R.layout.report_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = reportRV.apply{
            this.layoutManager=myLayoutManager
            this.adapter=myAdapter
        }

        titleTV.text="Report from "+ SimpleDateFormat("dd.MM.yyyy").format(Date())
    }
}