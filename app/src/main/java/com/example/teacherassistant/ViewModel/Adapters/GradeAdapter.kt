package com.example.teacherassistant.ViewModel.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
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
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int =
        grades?.value?.size ?: 0
}