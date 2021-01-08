package com.example.teacherassistant.View.InputView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.CourseViewModel
import com.example.teacherassistant.ViewModel.StudentViewModel
import kotlinx.android.synthetic.main.add_course_layout.*
import kotlinx.android.synthetic.main.add_student_layout.*

class AddEditStudentFragment : Fragment() {
    private lateinit var viewModel : StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel= ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        return inflater.inflate(R.layout.add_student_layout, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //add student
        if(viewModel.currentStudent==null){
            saveStudentName.text="Add Student"
            saveStudentName.setOnClickListener {
                if(!studentFirstNameInput.text.isNullOrEmpty()&&!studentLastNameInput.text.isNullOrEmpty())
                    viewModel.addStudent(studentFirstNameInput.text.toString(),studentLastNameInput.text.toString())
                activity?.onBackPressed()
            }
        }
        //edit student
        else{
            studentFirstNameInput.setText(viewModel.currentStudent!!.firstName)
            studentLastNameInput.setText(viewModel.currentStudent!!.lastName)
            saveStudentName.text="Edit Student"
            saveStudentName.setOnClickListener {
                if(!studentFirstNameInput.text.isNullOrEmpty()&&!studentLastNameInput.text.isNullOrEmpty())
                    viewModel.editStudent(studentFirstNameInput.text.toString(),studentLastNameInput.text.toString())
                activity?.onBackPressed()
            }
        }


    }
}