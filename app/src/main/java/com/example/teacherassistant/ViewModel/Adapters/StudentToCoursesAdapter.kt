package com.example.teacherassistant.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Student
import com.example.teacherassistant.R

class StudentToCoursesAdapter (
    var students: LiveData<List<Student>>
) : RecyclerView.Adapter<StudentToCoursesAdapter.StudentToCoursesHolder>()
{
    class StudentToCoursesHolder(val view: View) : RecyclerView.ViewHolder(view)

    var studentsChecked : MutableList<Student> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentToCoursesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_checkbox,parent,false) as View
        return StudentToCoursesHolder(view)
    }

    override fun onBindViewHolder(holder: StudentToCoursesHolder, position: Int) {
        val item=holder.view.findViewById<CheckBox>(R.id.itemCheckBox)
        if(item!=null){
            item.text="${students.value?.get(position)?.firstName} ${students.value?.get(position)?.lastName}"
            item.setOnClickListener{
                if(item.isChecked) studentsChecked.add(students.value!![position])

                else studentsChecked.remove(students.value?.get(position))
            }
        }
    }

    override fun getItemCount(): Int =
        students.value?.size ?: 0
}