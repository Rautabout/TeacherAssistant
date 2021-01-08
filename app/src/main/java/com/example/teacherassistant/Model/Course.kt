package com.example.teacherassistant.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_table")
data class Course(@PrimaryKey(autoGenerate = true) val id : Int, val courseName : String) {

}
