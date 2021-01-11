package com.example.teacherassistant.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Grade
import com.example.teacherassistant.R

class GradeAdapter(
    var grades: LiveData<List<Grade>>?,
    val onClickDelete : (g: Grade)-> Unit,
    val changeCurrentGrade : (g: Grade) -> Unit
    ) :RecyclerView.Adapter<GradeAdapter.GradeHolder>(){
    class GradeHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_grade_row, parent, false) as View

        return GradeHolder(view)
    }

    override fun onBindViewHolder(holder: GradeHolder, position: Int) {
        val gradeItemTV=holder.view.findViewById<TextView>(R.id.gradeItemTV)
        gradeItemTV.text=grades?.value?.get(position)?.grade
        val noteItemTV=holder.view.findViewById<TextView>(R.id.noteItemTV)
        noteItemTV.text=grades?.value?.get(position)?.note

        val editItemButton=holder.view.findViewById<Button>(R.id.editItemButton)
        editItemButton.setOnClickListener {
            view -> view.findNavController().navigate(R.id.action_manageGradeFragment_to_addEditGradeFragment)
            val thisItem=grades?.value?.get(position)
            if(thisItem!=null) changeCurrentGrade(thisItem)
        }

        val deleteItemButton=holder.view.findViewById<Button>(R.id.deleteItemButton)
        deleteItemButton.setOnClickListener {
            val thisItem=grades?.value?.get(position)
            if(thisItem!=null) onClickDelete(thisItem)
        }
    }

    override fun getItemCount(): Int =
        grades?.value?.size ?: 0
}