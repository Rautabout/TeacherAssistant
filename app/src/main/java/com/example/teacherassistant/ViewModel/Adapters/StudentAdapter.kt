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

class StudentAdapter (var students: LiveData<List<Student>>,
                      val onClickDelete: (s: Student) -> Unit,
                      val changeCurrentStudent: (s: Student) -> Unit) : RecyclerView.Adapter<StudentAdapter.StudentHolder>() {

    class StudentHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_manage_row, parent, false) as View

        return StudentHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: StudentHolder, position: Int) {
        // student name
        val itemButton = holder.view.findViewById<Button>(R.id.itemButton)
        itemButton.text =
            "${students.value?.get(position)?.firstName} ${students.value?.get(position)?.lastName}"
        itemButton.setOnClickListener {
            view->view.findNavController().navigate(R.id.action_manageStudentsFragment_to_coursesFromStudentFragment)
            val thisItem = students.value?.get(position)
            if (thisItem != null) changeCurrentStudent(thisItem)
        }

        // student edit
        val editItemButton = holder.view.findViewById<Button>(R.id.editItemButton)
        editItemButton.setOnClickListener { view ->
            view.findNavController()
                .navigate(R.id.action_manageStudentsFragment_to_addEditStudentFragment)
            val thisItem = students.value?.get(position)
            if (thisItem != null) changeCurrentStudent(thisItem)
        }

        // student delete
        val deleteItemButton = holder.view.findViewById<Button>(R.id.deleteItemButton)
        deleteItemButton.setOnClickListener {
            val thisItem = students.value?.get(position)
            if (thisItem != null) onClickDelete(thisItem)
        }
    }

    override fun getItemCount(): Int =
        students.value?.size ?: 0
}