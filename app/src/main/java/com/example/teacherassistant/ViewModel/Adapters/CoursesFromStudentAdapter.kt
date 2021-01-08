package com.example.teacherassistant.ViewModel.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.R

class CoursesFromStudentAdapter (
    var courses: LiveData<List<Course>>,
    val onClickRemove: (c: Course) -> Unit,
    val changeCurrentCourse: (c: Course) -> Unit) : RecyclerView.Adapter<CoursesFromStudentAdapter.CoursesFromStudentHolder>() {

    class CoursesFromStudentHolder (val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesFromStudentHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_merge_row,parent,false) as View

        return CoursesFromStudentHolder(view)
    }

    override fun onBindViewHolder(holder: CoursesFromStudentHolder, position: Int) {
        //course
        val itemButton=holder.view.findViewById<Button>(R.id.itemButton)
        itemButton.text="${courses.value?.get(position)?.courseName}"
        itemButton.setOnClickListener {
            view->view.findNavController().navigate(R.id.action_coursesFromStudentFragment_to_manageGradeFragment)
            val thisItem=courses.value?.get(position)
            if(thisItem!=null)changeCurrentCourse(thisItem)
        }


        //delete from student
        val deleteItemButton= holder.view.findViewById<Button>(R.id.deleteItemButton)
        deleteItemButton.setOnClickListener {
            val thisItem=courses.value?.get(position)
            if(thisItem!=null) {
                onClickRemove(thisItem)
            }
        }
    }

    override fun getItemCount(): Int =
        courses.value?.size ?: 0


}