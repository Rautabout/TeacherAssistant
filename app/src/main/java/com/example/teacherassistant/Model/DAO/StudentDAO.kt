package com.example.teacherassistant.Model.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teacherassistant.Model.Student

@Dao
interface StudentDAO {
    @Insert
    suspend fun insertStudent(student : Student)

    @Delete
    suspend fun deleteStudent(student : Student)

    @Update
    suspend fun editStudent(student : Student)

    @Query("SELECT * FROM student_table ORDER BY firstName, lastName")
    fun getAllStudents() : LiveData<List<Student>>
}
