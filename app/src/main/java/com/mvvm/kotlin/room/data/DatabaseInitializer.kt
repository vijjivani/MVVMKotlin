package com.mvvm.kotlin.room.data

import com.mvvm.kotlin.room.data.db.AppDataBase
import com.mvvm.kotlin.room.model.Employee
import io.reactivex.Single

class DatabaseInitializer {
    companion object {
        val TAG = DatabaseInitializer::class.java.name

        fun insertEmployee(db: AppDataBase, employee: Employee): Single<Long> {
            return db.employeeDao().insert(employee)
        }

        fun getAllEmployees(db: AppDataBase): Single<List<Employee>> {
            return db.employeeDao().getAll()
        }

        fun deleteEmployee(db: AppDataBase,employee: Employee):Single<Int> {
            return db.employeeDao().delete(employee)
        }

        fun updateEmployee(db: AppDataBase,employee: Employee):Single<Int> {
            return db.employeeDao().update(employee.name?:"",employee.email?:"",employee.phone?:"",employee.roll_no)
        }


    }
}