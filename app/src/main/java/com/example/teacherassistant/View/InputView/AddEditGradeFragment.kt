package com.example.teacherassistant.View.InputView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.CourseStudentViewModel
import com.example.teacherassistant.ViewModel.GradeViewModel

class AddEditGradeFragment : Fragment() {
    private lateinit var gradeViewModel : GradeViewModel
    private lateinit var courseStudentViewModel: CourseStudentViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_grade_layout,container,false)
    }
}