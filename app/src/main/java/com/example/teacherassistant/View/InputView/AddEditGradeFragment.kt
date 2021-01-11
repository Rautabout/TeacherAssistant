package com.example.teacherassistant.View.InputView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.CourseStudentViewModel
import com.example.teacherassistant.ViewModel.GradeViewModel
import kotlinx.android.synthetic.main.add_grade_layout.*
import java.text.SimpleDateFormat
import java.util.*

class AddEditGradeFragment : Fragment() {
    private lateinit var gradeViewModel : GradeViewModel
    private lateinit var courseStudentViewModel: CourseStudentViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gradeViewModel=ViewModelProvider(requireActivity()).get(GradeViewModel::class.java)
        courseStudentViewModel=ViewModelProvider(requireActivity()).get(CourseStudentViewModel::class.java)

        return inflater.inflate(R.layout.add_grade_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //spinner implementation
        context?.let {
            ArrayAdapter.createFromResource(it,R.array.grades,android.R.layout.simple_spinner_item)
                    .also { adapter ->
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                        gradeSpinner.adapter=adapter
                    }
        }

        //grade add
        if(gradeViewModel.currentGrade==null){
            saveGradeName.setOnClickListener {
                if(gradeSpinner.selectedItem!=null){
                    gradeViewModel.addGrade(
                            courseStudentViewModel.currentCourseStudent!!.id,
                            gradeSpinner.selectedItem.toString(),
                            gradeNotesInput.text.toString(),
                            SimpleDateFormat("dd.MM.yyyy").format(Date())
                    )
                    activity?.onBackPressed()
                }
            }
        }

        //grade edit
        else{
            gradeSpinner.setSelection(resources.getStringArray(R.array.grades).indexOf(gradeViewModel.currentGrade!!.grade.toString()))
            gradeNotesInput.setText(gradeViewModel.currentGrade!!.note)

            saveGradeName.setOnClickListener {
                if(gradeSpinner.selectedItem!=null){
                    gradeViewModel.editGrade(
                            courseStudentViewModel.currentCourseStudent!!.id,
                            gradeSpinner.selectedItem.toString(),
                            gradeNotesInput.text.toString(),
                            SimpleDateFormat("dd.MM.yyyy").format(Date())
                    )
                    activity?.onBackPressed()
                }
            }
        }
    }
}