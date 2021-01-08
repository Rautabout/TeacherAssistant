package com.example.teacherassistant.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.Adapters.CourseAdapter
import com.example.teacherassistant.ViewModel.CourseViewModel
import kotlinx.android.synthetic.main.manage_layout.*

class ManageCoursesFragment : Fragment(){
    private lateinit var recyclerView: RecyclerView
    private lateinit var myLayoutManager: RecyclerView.LayoutManager
    private lateinit var myAdapter : CourseAdapter
    private lateinit var viewModel: CourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        myLayoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(requireActivity()).get(CourseViewModel::class.java)
        myAdapter= CourseAdapter(viewModel.courses,{d->viewModel.deleteCourse(d)},{s->viewModel.setCurrentCourse(s)})
        viewModel.courses.observe(viewLifecycleOwner,{myAdapter.notifyDataSetChanged()})
        viewModel.setCurrentCourse(null)
        return inflater.inflate(R.layout.manage_layout,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView=manageRecyclerView.apply {
            this.layoutManager=myLayoutManager
            this.adapter=myAdapter
        }

        manageAddButton.text="Add Course"
        manageAddButton.setOnClickListener {
            view.findNavController().navigate(
                R.id.action_manageCoursesFragment_to_addEditCourseFragment
            )
        }
    }

}