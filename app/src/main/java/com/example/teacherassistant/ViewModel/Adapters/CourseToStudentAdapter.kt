package com.example.teacherassistant.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.R

class CourseToStudentAdapter(
    var courses:LiveData<List<Course>>) : RecyclerView.Adapter<CourseToStudentAdapter.CourseToStudentHolder>()
 {
     class CourseToStudentHolder(val view: View) : RecyclerView.ViewHolder(view)

     var coursesChecked : MutableList<Course> = ArrayList()

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseToStudentHolder {
         val view = LayoutInflater.from(parent.context)
             .inflate(R.layout.rv_checkbox,parent,false) as View
         return CourseToStudentHolder(view)
     }

     override fun onBindViewHolder(holder: CourseToStudentHolder, position: Int) {
         val item=holder.view.findViewById<CheckBox>(R.id.itemCheckBox)
         if(item!=null){
             item.text=courses.value?.get(position)?.courseName
             item.setOnClickListener{
                 if(item.isChecked) coursesChecked.add(courses.value!![position])

                 else coursesChecked.remove(courses.value?.get(position))
             }
         }
     }

     override fun getItemCount(): Int =
         courses.value?.size ?: 0
 }