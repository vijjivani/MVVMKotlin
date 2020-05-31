package com.mvvm.kotlin.room.view

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity
import com.mvvm.kotlin.R
import com.mvvm.kotlin.room.model.Employee
import com.mvvm.kotlin.room.view.fragment.AddEmployeeFragment
import com.mvvm.kotlin.room.view.fragment.EmployeeListFragment





class HomeActivity : AppCompatActivity(), EmployeeListFragment.NavigateToAddEmployee,
    AddEmployeeFragment.NavigateToEmployeeList {
    private lateinit var myObject: Employee
    override fun loadEmployeeList() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, EmployeeListFragment.newInstance())
            .commitNow()
    }

    override fun loadEmployee(myObject:Employee) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, AddEmployeeFragment.newInstance(myObject))
            .commitNow()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, EmployeeListFragment.newInstance())
                .commitNow()
        }
    }


}
