package com.example.teacherassistant.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.Model.AssistantDatabase
import com.example.teacherassistant.Model.Course
import com.example.teacherassistant.Model.Repos.CourseRepo
import kotlinx.coroutines.launch

class CourseViewModel(application: Application) : AndroidViewModel(application) {
    var currentCourse: Course? = null

    private val courseRepository: CourseRepo =
        CourseRepo(AssistantDatabase.getDatabase(application).courseDAO())
    val courses: LiveData<List<Course>> = courseRepository.readAll

    fun addCourse(name: String) {
        viewModelScope.launch { courseRepository.add(Course(id = 0, courseName = name)) }
    }

    fun deleteCourse(course: Course) {
        viewModelScope.launch { courseRepository.delete(course) }
    }

    fun editCourse(name: String) {
        viewModelScope.launch {
            if (currentCourse != null)
                courseRepository.edit(Course(id = currentCourse!!.id, courseName = name))
        }
    }

    @JvmName("setCurrentCourse1")
    fun setCurrentCourse(course: Course?) {
        currentCourse = course
    }
}