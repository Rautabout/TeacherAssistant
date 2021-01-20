package com.example.teacherassistant.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teacherassistant.Model.DAO.*


@Database(entities = [Course::class, Student::class, CourseStudent::class,Grade::class,Notes::class],version = 7,exportSchema = false)
abstract class AssistantDatabase : RoomDatabase(){

    abstract fun courseDAO(): CourseDAO
    abstract fun studentDAO(): StudentDAO
    abstract fun courseStudentDAO(): CourseStudentDAO
    abstract fun gradeDAO(): GradeDAO
    abstract fun notesDAO(): NotesDAO


    companion object{
        @Volatile
        private var INSTANCE : AssistantDatabase? = null

        fun getDatabase(context: Context) : AssistantDatabase {
            val tempInstance=INSTANCE

            if(tempInstance!=null)
                return tempInstance
            else
                synchronized(this)
                {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AssistantDatabase::class.java,
                        "assistant_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE=instance
                    return instance
                }
        }
    }
}