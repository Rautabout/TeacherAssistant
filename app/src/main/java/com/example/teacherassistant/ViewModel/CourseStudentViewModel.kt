package com.example.teacherassistant.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.Model.AssistantDatabase
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.CourseStudent
import com.example.teacherassistant.Model.Repos.CourseStudentRepo
import com.example.teacherassistant.Model.Student
import kotlinx.coroutines.launch

class CourseStudentViewModel(application: Application) : AndroidViewModel(application) {

    //set end deset courses and students
    private val db=AssistantDatabase.getDatabase(application)
    private val courseStudentRepo : CourseStudentRepo = CourseStudentRepo(db.courseStudentDAO())

    fun addCourseStudentConnection(course : Course, student : Student) {
        viewModelScope.launch { courseStudentRepo.add(
            CourseStudent(id = 0, course_id = course.id, student_id = student.id)
        ) }
    }

    fun deleteStudentSubjectConnection(course : Course, student : Student) {
        viewModelScope.launch { courseStudentRepo.delete(course.id, student.id) }
    }


    //student's courses


    //course's students

}