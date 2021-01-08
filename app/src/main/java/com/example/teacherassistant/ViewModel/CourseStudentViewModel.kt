package com.example.teacherassistant.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
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

    fun deleteStudentCourseConnection(course : Course, student : Student) {
        viewModelScope.launch { courseStudentRepo.delete(course.id, student.id) }
    }


    //student's courses
    var coursesFromStudent: LiveData<List<Course>>?=null
    var coursesNotInStudent: LiveData<List<Course>>?=null

    fun setCoursesFromStudent(student: Student?){
        if (student!=null) {
            coursesFromStudent = courseStudentRepo.getCoursesFromStudent(student.id)
        }
    }

    fun setCoursesNotInStudent(student: Student?){
        if(student!=null){
            coursesNotInStudent=courseStudentRepo.getCoursesOutOfStudent(student.id)
        }
    }

    //course's students
    var studentsFromCourse: LiveData<List<Student>>?=null
    var studentsNotInStudent: LiveData<List<Student>>?=null

    fun setStudentsFromCourse(course: Course?){
        if (course!=null) {
            studentsFromCourse = courseStudentRepo.getStudentsFromCourse(course.id)
        }
    }

    fun setStudentsNotInStudent(course: Course?){
        if(course!=null){
            studentsNotInStudent=courseStudentRepo.getStudentsOutOfCourse(course.id)
        }
    }

    //set
    var currentCourseStudent: CourseStudent?=null
    val connections : LiveData<List<CourseStudent>> = courseStudentRepo.readAll
    fun setCurrentCourseStudent(course: Course?, student: Student?){
        if(course!=null && student!=null){
            currentCourseStudent=connections.value?.find{
                x->x.student_id==student.id && x.course_id==course.id
            }
        }
    }

}