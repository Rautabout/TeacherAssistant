package com.example.teacherassistant.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.Model.AssistantDatabase
import com.example.teacherassistant.Model.CourseStudent
import com.example.teacherassistant.Model.Grade
import com.example.teacherassistant.Model.Repos.GradeRepo
import kotlinx.coroutines.launch

class GradeViewModel (application: Application) : AndroidViewModel(application) {
    var currentGrade : Grade? = null
    private val database = AssistantDatabase.getDatabase(application)
    private val gradeRepo : GradeRepo = GradeRepo(database.gradeDAO())
    val gradesFromToday : LiveData<List<Grade>> = gradeRepo.getAllGradesFromToday

    fun addGrade(courseStudentID : Int, grade : Int, note : String, date : String)
    {
        viewModelScope.launch { gradeRepo.add(Grade(id = 0,
            courseStudent_id = courseStudentID, grade = grade, note = note, date = date)) }
    }

    fun editMark(courseStudentID : Int, grade : Int, note : String, date : String)
    {
        viewModelScope.launch { if (currentGrade != null) gradeRepo.edit(
            Grade(id = currentGrade!!.id,
                courseStudent_id = courseStudentID, grade = grade, note = note, date = date))
        }
    }

    fun deleteMark(grade : Grade)
    {
        viewModelScope.launch { gradeRepo.delete(grade) }
    }

    @JvmName("setCurrentMark1")
    fun setCurrentMark(grade: Grade?) { currentGrade = grade }

    var gradesFromCurrentCourseStudent : LiveData<List<Grade>>? = null
    fun setMarksFromCurrentStudentSubject(courseStudent : CourseStudent?) {
        if (courseStudent != null)
            gradesFromCurrentCourseStudent = gradeRepo.getAllGradesFromCourseStudent(courseStudent.id)
    }
}