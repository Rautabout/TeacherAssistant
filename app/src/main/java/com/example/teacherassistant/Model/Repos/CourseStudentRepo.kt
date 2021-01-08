package com.example.teacherassistant.Model.Repos

import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.CourseStudent
import com.example.teacherassistant.Model.DAO.CourseStudentDAO

class CourseStudentRepo(private val courseStudentDao : CourseStudentDAO) {
    val readAll : LiveData<List<CourseStudent>> = courseStudentDao.getAllConnections()
    fun getStudentsFromSubject(courseID : Int) = courseStudentDao.getAllStudentsFromSubject(courseID)
    fun getStudentsOutOfSubject(courseID : Int) = courseStudentDao.getAllStudentsOutOfSubject(courseID)
    fun getCoursesFromStudent(studentID : Int) = courseStudentDao.getAllCoursesFromStudent(studentID)
    fun getCoursesOutOfStudent(studentID : Int) = courseStudentDao.getAllCoursesOutOfStudent(studentID)
    suspend fun add(courseStudent: CourseStudent) = courseStudentDao.insertStudentSubject(courseStudent)
    suspend fun delete(courseID : Int, studentID : Int) = courseStudentDao.deleteStudentSubject(courseID, studentID)
}