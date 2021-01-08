package com.example.teacherassistant.Model.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.Grade

@Dao
interface GradeDAO {
    @Insert
    suspend fun insertGrade(grade : Grade)

    @Delete
    suspend fun deleteGrade(grade : Grade)

    @Update
    suspend fun editGrade(grade : Grade)

    @Query("SELECT * FROM grade_table WHERE courseStudent_id == :courseStudentID")
    fun getAllGradesFromCourseStudent(courseStudentID : Int) : LiveData<List<Grade>>

    @Query("SELECT * FROM grade_table WHERE date == :date")
    fun getAllGradesFromGivenDay(date : String): LiveData<List<Grade>>

}