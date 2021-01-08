package com.example.teacherassistant.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.R

class CourseAdapter(
    var courses: LiveData<List<Course>>,
    val onClickDelete: (c : Course)-> Unit,
    val changeCurrentCourse: (c : Course) -> Unit
) :RecyclerView.Adapter<CourseAdapter.CourseHolder>(){
    class CourseHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_manage_row, parent, false) as View

        return CourseHolder(view)
    }

    override fun onBindViewHolder(holder: CourseHolder, position: Int) {

        //course name
        val itemButton = holder.view.findViewById<Button>(R.id.itemButton)
        itemButton.text=courses.value?.get(position)?.courseName
        itemButton.setOnClickListener {
            view->view.findNavController().navigate(R.id.action_manageCoursesFragment_to_studentsFromCourseFragment)
            val thisItem=courses.value?.get(position)
            if(thisItem!=null) changeCurrentCourse(thisItem)
        }

        //course edit
        val editItemButton=holder.view.findViewById<Button>(R.id.editItemButton)
        editItemButton.setOnClickListener {
            view->view.findNavController().navigate(R.id.action_manageCoursesFragment_to_addEditCourseFragment)
            val thisItem=courses.value?.get(position)
            if(thisItem!=null) changeCurrentCourse(thisItem)
        }

        //course delete
        val deleteItemButton = holder.view.findViewById<Button>(R.id.deleteItemButton)
        deleteItemButton.setOnClickListener {
            val thisItem = courses.value?.get(position)
            if (thisItem != null) onClickDelete(thisItem)
        }
    }

    override fun getItemCount(): Int =
        courses.value?.size ?: 0
}