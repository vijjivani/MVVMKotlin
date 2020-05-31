package com.mvvm.kotlin.room.data.dao

import androidx.room.*
import com.mvvm.kotlin.room.model.Employee
import io.reactivex.Single

@Dao
interface EmployeeDao {

    @Query("SELECT * FROM EMPLOYEE  ORDER BY name ASC")
    fun getAll(): Single<List<Employee>>

    @Query("SELECT COUNT(*) FROM EMPLOYEE")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(employee: Employee): Single<Long>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(employees: List<Employee>): LongArray

    @Delete
    fun delete(employee: Employee): Single<Int>

    @Query("UPDATE employee SET name=:name,email=:email,phone_no=:phone WHERE roll_no = :roll_no")
    fun update(name:String,email:String,phone:String,roll_no:Int): Single<Int>





}