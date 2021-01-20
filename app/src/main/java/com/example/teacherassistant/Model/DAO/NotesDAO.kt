package com.example.teacherassistant.Model.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.Notes
import com.example.teacherassistant.Model.Student

@Dao
interface NotesDAO {
    @Insert
    suspend fun insertNote(notes: Notes)

    @Delete
    suspend fun deleteNote(notes: Notes)

    @Update
    suspend fun editNote(notes: Notes)

    @Query("SELECT * FROM notes_table ORDER BY date")
    fun getAllNotes() : LiveData<List<Notes>>
}