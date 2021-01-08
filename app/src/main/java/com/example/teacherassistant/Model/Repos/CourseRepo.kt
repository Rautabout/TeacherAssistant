package com.example.teacherassistant.Model.Repos

import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.DAO.CourseDAO

class CourseRepo(private val courseDao: CourseDAO) {
    val readAll : LiveData<List<Course>> = courseDao.getAllCourses()
    suspend fun add(subject : Course) = courseDao.insertCourse(subject)
    suspend fun delete(subject : Course) = courseDao.deleteCourse(subject)
    suspend fun edit(subject : Course) = courseDao.editCourse(subject)
}