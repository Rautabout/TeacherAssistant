package com.example.teacherassistant.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.teacherassistant.Model.AssistantDatabase
import com.example.teacherassistant.Model.Repos.StudentRepo
import com.example.teacherassistant.Model.Student
import kotlinx.coroutines.launch

class StudentViewModel (application: Application) : AndroidViewModel(application) {
    var currentStudent: Student? = null

    private val studentRepository: StudentRepo =
        StudentRepo(AssistantDatabase.getDatabase(application).studentDAO())
    val students: LiveData<List<Student>> = studentRepository.readAll

    fun addStudent(name: String, lastName: String) {
        viewModelScope.launch {
            studentRepository.add(
                Student(
                    id = 0,
                    firstName = name,
                    lastName = lastName
                )
            )
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch { studentRepository.delete(student) }
    }

    fun editStudent(name: String, lastName: String) {
        viewModelScope.launch {
            if (currentStudent != null)
                studentRepository.edit(
                    Student(
                        id = currentStudent!!.id,
                        firstName = name,
                        lastName = lastName
                    )
                )
        }
    }

    @JvmName("setCurrentStudent1")
    fun setCurrentStudent(student: Student?) {
        currentStudent = student
    }
}