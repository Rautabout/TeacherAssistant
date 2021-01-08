package com.example.teacherassistant.Model.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.Course


@Dao
interface CourseDAO {

    @Insert
    suspend fun insertCourse(course : Course)

    @Delete
    suspend fun deleteCourse(course : Course)

    @Update
    suspend fun editCourse(course : Course)

    @Query("SELECT * FROM course_table")
    fun getAllCourses() : LiveData<List<Course>>
}