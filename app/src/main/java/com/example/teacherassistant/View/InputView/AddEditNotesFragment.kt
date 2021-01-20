package com.example.teacherassistant.View.InputView

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.teacherassistant.R
import com.example.teacherassistant.ViewModel.NotesViewModel
import kotlinx.android.synthetic.main.add_notes_layout.*
import kotlinx.android.synthetic.main.add_student_layout.*
import kotlinx.android.synthetic.main.add_student_layout.saveStudentName
import java.text.SimpleDateFormat
import java.util.*

class AddEditNotesFragment : Fragment() {
    private lateinit var notesViewModel:NotesViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notesViewModel=ViewModelProvider(requireActivity()).get(NotesViewModel::class.java)
        return inflater.inflate(R.layout.add_notes_layout,container,false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //add student
        if(notesViewModel.currentNote==null){
            saveNoteName.text="Add Note"
            saveNoteName.setOnClickListener {
                if(!noteNotesInput.text.isNullOrEmpty())
                    notesViewModel.addNote(noteNotesInput.text.toString(),
                        SimpleDateFormat("dd.MM.yyyy").format(Date()))
                activity?.onBackPressed()
            }
        }
        //edit student
        else{
            noteNotesInput.setText(notesViewModel.currentNote!!.note)
            saveNoteName.text="Edit Note"
            saveNoteName.setOnClickListener {
                if(!noteNotesInput.text.isNullOrEmpty())
                    notesViewModel.editNote(noteNotesInput.text.toString(),SimpleDateFormat("dd.MM.yyyy").format(Date()))
                activity?.onBackPressed()
            }
        }
    }
}