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
import com.example.teacherassistant.ViewModel.Adapters.StudentAdapter
import com.example.teacherassistant.ViewModel.StudentViewModel
import kotlinx.android.synthetic.main.manage_layout.*

class ManageStudentsFragment : Fragment() {
    private lateinit var recyclerView : RecyclerView
    private lateinit var myLayoutManager : RecyclerView.LayoutManager
    private lateinit var myAdapter : StudentAdapter
    private lateinit var viewModel: StudentViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        myLayoutManager = LinearLayoutManager(context)
        viewModel = ViewModelProvider(requireActivity()).get(StudentViewModel::class.java)
        myAdapter = StudentAdapter(viewModel.students, { s -> viewModel.deleteStudent(s) }, { w -> viewModel.setCurrentStudent(w) })
        viewModel.students.observe(viewLifecycleOwner, { myAdapter.notifyDataSetChanged() })
        viewModel.setCurrentStudent(null)
        return inflater.inflate(R.layout.manage_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = manageRecyclerView.apply {
            this.layoutManager = myLayoutManager
            this.adapter = myAdapter
        }

        manageAddButton.text="Add Student"
        manageAddButton.setOnClickListener {
            view.findNavController().navigate(
                R.id.action_manageStudentsFragment_to_addEditStudentFragment
            )
        }
    }

}