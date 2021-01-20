package com.example.teacherassistant.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class Notes(@PrimaryKey(autoGenerate = true) val id : Int, val note : String, val date : String)  {
}