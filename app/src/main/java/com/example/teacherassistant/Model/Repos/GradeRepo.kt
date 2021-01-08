package com.example.teacherassistant.Model.Repos

import androidx.lifecycle.LiveData
import com.example.teacherassistant.Model.DAO.GradeDAO
import com.example.teacherassistant.Model.Grade
import java.text.SimpleDateFormat
import java.util.*

class GradeRepo(private val gradeDao: GradeDAO) {
    val getAllGradesFromToday : LiveData<List<Grade>>
            = gradeDao.getAllGradesFromGivenDay(SimpleDateFormat("dd-MM-yyyy").format(Date()))
    fun getAllGradesFromCourseStudent(courseStudentID : Int)
            = gradeDao.getAllGradesFromCourseStudent(courseStudentID)
    suspend fun add(mark: Grade) = gradeDao.insertGrade(mark)
    suspend fun delete(mark: Grade) = gradeDao.deleteGrade(mark)
    suspend fun edit(mark: Grade) = gradeDao.editGrade(mark)
}