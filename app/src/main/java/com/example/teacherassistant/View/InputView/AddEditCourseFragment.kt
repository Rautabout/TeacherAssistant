package com.example.teacherassistant.View.InputView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.CourseViewModel
import kotlinx.android.synthetic.main.add_course_layout.*

class AddEditCourseFragment : Fragment() {
    private lateinit var viewModel : CourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel= ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)
        return inflater.inflate(R.layout.add_course_layout, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //add course
        if(viewModel.currentCourse==null){
            saveCourseName.text="Add Course"
            saveCourseName.setOnClickListener {
                if(!courseNameInput.text.isNullOrEmpty())
                    viewModel.addCourse(courseNameInput.text.toString())
                activity?.onBackPressed()
            }
        }
        //edit student
        else{
            courseNameInput.setText(viewModel.currentCourse!!.courseName)
            saveCourseName.text="Edit Course"
            saveCourseName.setOnClickListener {
                if(!courseNameInput.text.isNullOrEmpty())
                    viewModel.editCourse(courseNameInput.text.toString())
                activity?.onBackPressed()
            }
        }


    }
}