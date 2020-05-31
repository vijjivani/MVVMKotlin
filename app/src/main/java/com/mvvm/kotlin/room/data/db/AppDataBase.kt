package com.mvvm.kotlin.room.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mvvm.kotlin.room.data.dao.EmployeeDao
import com.mvvm.kotlin.room.model.Employee

@Database(entities = [Employee::class], version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    companion object {
        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getDatabase(
            context: Context
        ): AppDataBase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "employee-database"
                )
                    .allowMainThreadQueries()
                    .build()
            }

            return INSTANCE as AppDataBase

        }

    }

}