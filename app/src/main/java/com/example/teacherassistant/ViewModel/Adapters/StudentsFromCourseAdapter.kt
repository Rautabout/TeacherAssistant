package com.example.teacherassistant.ViewModel.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.R

class StudentsFromCourseAdapter(
    var students: LiveData<List<Student>>,
    val onClickRemove: (s: Student) -> Unit,
    val changeCurrentStudent: (s: Student) -> Unit) : RecyclerView.Adapter<StudentsFromCourseAdapter.StudentsFromCourseHolder>() {

    class StudentsFromCourseHolder (val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentsFromCourseHolder {
        val view=LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_merge_row,parent,false) as View

        return StudentsFromCourseHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StudentsFromCourseHolder, position: Int) {
        //student
        val itemButton=holder.view.findViewById<Button>(R.id.itemButton)
        itemButton.text="${students.value?.get(position)?.firstName} ${students.value?.get(position)?.lastName}"
        itemButton.setOnClickListener {
            view-> view.findNavController().navigate(R.id.action_studentsFromCourseFragment_to_manageGradeFragment)
            val thisItem=students.value?.get(position)
            if(thisItem!=null)changeCurrentStudent(thisItem)
        }


        //delete from course
        val deleteItemButton= holder.view.findViewById<Button>(R.id.deleteItemButton)
        deleteItemButton.setOnClickListener {
            val thisItem=students.value?.get(position)
            if(thisItem!=null) {
                onClickRemove(thisItem)
            }
        }
    }

    override fun getItemCount(): Int =
        students.value?.size ?: 0

}