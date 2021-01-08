package com.example.teacherassistant.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "grade_table", foreignKeys = [
    ForeignKey(entity = CourseStudent::class, parentColumns = ["id"],
    childColumns = ["courseStudent_id"], onDelete = ForeignKey.CASCADE)
])
data class Grade(@PrimaryKey(autoGenerate = true)val id : Int, val courseStudent_id : Int, val grade : Int, val note : String, val date : String)
