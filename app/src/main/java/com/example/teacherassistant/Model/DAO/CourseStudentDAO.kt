package com.example.teacherassistant.Model.DAO

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.CourseStudent
import com.example.teacherassistant.Model.Student

@Dao
interface CourseStudentDAO {
    @Insert
    suspend fun insertStudentSubject(courseStudent: CourseStudent)

    @Query("DELETE FROM course_student_table WHERE course_id == :courseID AND student_id == :studentID")
    suspend fun deleteStudentSubject(courseID : Int, studentID : Int)

    @Query("SELECT * FROM student_table WHERE id IN (SELECT student_id FROM course_student_table WHERE course_id == :courseID) ORDER BY firstName,lastName ")
    fun getAllStudentsFromSubject(courseID : Int) : LiveData<List<Student>>

    @Query("SELECT * FROM student_table WHERE id NOT IN (SELECT student_id FROM course_student_table WHERE course_id == :courseID) ORDER BY firstName, lastName")
    fun getAllStudentsOutOfSubject(courseID : Int) : LiveData<List<Student>>

    @Query("SELECT * FROM course_table WHERE id IN (SELECT course_id FROM course_student_table WHERE student_id == :studentID)")
    fun getAllCoursesFromStudent(studentID : Int) : LiveData<List<Course>>

    @Query("SELECT * FROM course_table WHERE id NOT IN (SELECT course_id FROM course_student_table WHERE student_id == :studentID)")
    fun getAllCoursesOutOfStudent(studentID : Int) : LiveData<List<Course>>

    @Query("SELECT * FROM course_student_table")
    fun getAllConnections() : LiveData<List<CourseStudent>>
}