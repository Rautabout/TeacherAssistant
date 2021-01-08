package com.example.teacherassistant.Model.Repos

import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.DAO.StudentDAO
import com.example.teacherassistant.Model.Student

class StudentRepo(private val studentDao: StudentDAO) {
    val readAll : LiveData<List<Student>> = studentDao.getAllStudents()
    suspend fun add(student:Student) = studentDao.insertStudent(student)
    suspend fun delete(student:Student) = studentDao.deleteStudent(student)
    suspend fun edit(student:Student) = studentDao.editStudent(student)
}