package com.example.teacherassistant.Model.Repos

import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.DAO.NotesDAO
import com.example.teacherassistant.Model.Notes
import com.example.teacherassistant.Model.Student

class NotesRepo(private val notesDao: NotesDAO) {
    val readAll : LiveData<List<Notes>> = notesDao.getAllNotes()
    suspend fun add(notes: Notes) = notesDao.insertNote(notes)
    suspend fun delete(notes: Notes) = notesDao.deleteNote(notes)
    suspend fun edit(notes: Notes) = notesDao.editNote(notes)
}