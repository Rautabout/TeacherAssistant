package com.example.teacherassistant.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.Model.AssistantDatabase
import com.example.teacherassistant.Model.Grade
import com.example.teacherassistant.Model.Notes
import com.example.teacherassistant.Model.Repos.NotesRepo
import com.example.teacherassistant.Model.Student
import kotlinx.coroutines.launch


class NotesViewModel(application: Application) : AndroidViewModel(application) {
    var currentNote: Notes?=null

    private val notesRepository: NotesRepo=
        NotesRepo(AssistantDatabase.getDatabase(application).notesDAO())
    val notes: LiveData<List<Notes>> = notesRepository.readAll

    fun addNote(name: String,date:String){
        viewModelScope.launch { notesRepository.add(Notes(id=0,note = name,date=date)) }
    }
    fun editNote(name: String,date:String) {
        viewModelScope.launch {
            if (currentNote != null)
                notesRepository.edit(
                    Notes(
                        id = currentNote!!.id,
                        note = name,
                        date = date
                    )
                )
        }
    }
    fun deleteNote(note: Notes) {
        viewModelScope.launch { notesRepository.delete(note) }
    }

    @JvmName("setCurrentNote1")
    fun setCurrentNote(note: Notes?) {
        currentNote = note
    }

}