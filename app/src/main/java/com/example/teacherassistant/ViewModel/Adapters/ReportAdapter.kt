package com.example.teacherassistant.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Grade
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.CourseStudentViewModel
import com.example.teacherassistant.ViewModel.CourseViewModel
import com.example.teacherassistant.ViewModel.StudentViewModel
import org.w3c.dom.Text

class ReportAdapter(
        var grades: LiveData<List<Grade>>?,
        private val courseStudentViewModel: CourseStudentViewModel,
        private val studentViewModel: StudentViewModel,
        private val courseViewModel: CourseViewModel
) : RecyclerView.Adapter<ReportAdapter.ReportHolder>(){
    class ReportHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportHolder {
        val view=LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_report_row,parent,false) as View

        return ReportHolder(view)
    }

    override fun onBindViewHolder(holder: ReportHolder, position: Int) {
        val tempCourseStudentID=grades?.value?.get(position)?.courseStudent_id
        val tempCourseStudent=courseStudentViewModel.connections.value?.find { x -> x.id ==tempCourseStudentID }
        val tempStudent=studentViewModel.students.value?.find { x -> x.id== tempCourseStudent?.student_id }
        val tempCourse = courseViewModel.courses.value?.find { x -> x.id == tempCourseStudent?.course_id }

        val courseTextView=holder.view.findViewById<TextView>(R.id.courseItemTV)
        courseTextView.text=tempCourse?.courseName
        val studentTextView = holder.view.findViewById<TextView>(R.id.studentItemTV)
        studentTextView.text = tempStudent?.firstName + " " + tempStudent?.lastName
        val gradeTextView=holder.view.findViewById<TextView>(R.id.gradeItemTV)
        gradeTextView.text = grades?.value?.get(position)?.grade.toString()

    }

    override fun getItemCount(): Int =
            grades?.value?.size?:0
}