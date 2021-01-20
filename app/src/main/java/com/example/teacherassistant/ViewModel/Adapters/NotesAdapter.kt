package com.example.teacherassistant.ViewModel.Adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.teacherassistant.Model.Notes
import com.example.teacherassistant.R

class NotesAdapter (var notes: LiveData<List<Notes>>,
                    val onClickDelete: (n: Notes) -> Unit,
                    val changeCurrentNote: (n: Notes) -> Unit) : RecyclerView.Adapter<NotesAdapter.NotesHolder>() {

    class NotesHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_notes, parent, false) as View

        return NotesHolder(view)
    }

    override fun onBindViewHolder(holder: NotesHolder, position: Int) {
        val noteItemTV=holder.view.findViewById<TextView>(R.id.noteItemTV)
        noteItemTV.text=notes?.value?.get(position)?.note
        val dateItemTV=holder.view.findViewById<TextView>(R.id.dateItemTV)
        dateItemTV.text=notes?.value?.get(position)?.date



        // notes edit
        val editItemButton = holder.view.findViewById<Button>(R.id.editItemButton)
        editItemButton.setOnClickListener { view ->
            view.findNavController()
                .navigate(R.id.action_notesFragment_to_addEditNotesFragment)
            val thisItem = notes.value?.get(position)
            if (thisItem != null) changeCurrentNote(thisItem)
        }

        // notes delete
        val deleteItemButton = holder.view.findViewById<Button>(R.id.deleteItemButton)
        deleteItemButton.setOnClickListener {
            val thisItem = notes.value?.get(position)
            if (thisItem != null) onClickDelete(thisItem)
        }
    }

    override fun getItemCount(): Int =
        notes.value?.size ?: 0
}
