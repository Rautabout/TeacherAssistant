package com.example.teacherassistant.Model.Repos

import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.CourseStudent
import com.example.teacherassistant.Model.DAO.CourseStudentDAO

class CourseStudentRepo(private val courseStudentDao : CourseStudentDAO) {
    val readAll : LiveData<List<CourseStudent>> = courseStudentDao.getAllConnections()
    fun getStudentsFromCourse(courseID : Int) = courseStudentDao.getAllStudentsFromCourse(courseID)
    fun getStudentsOutOfCourse(courseID : Int) = courseStudentDao.getAllStudentsOutOfCourse(courseID)
    fun getCoursesFromStudent(studentID : Int) = courseStudentDao.getAllCoursesFromStudent(studentID)
    fun getCoursesOutOfStudent(studentID : Int) = courseStudentDao.getAllCoursesOutOfStudent(studentID)
    suspend fun add(courseStudent: CourseStudent) = courseStudentDao.insertStudentCourse(courseStudent)
    suspend fun delete(courseID : Int, studentID : Int) = courseStudentDao.deleteStudentCourse(courseID, studentID)
}