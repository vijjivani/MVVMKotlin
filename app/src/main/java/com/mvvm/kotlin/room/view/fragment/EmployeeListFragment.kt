package com.mvvm.kotlin.room.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mvvm.kotlin.R
import com.mvvm.kotlin.databinding.FragmentEmployeeListBinding
import com.mvvm.kotlin.room.model.Employee
import com.mvvm.kotlin.room.view.HomeActivity

class EmployeeListFragment : Fragment(),EmployeesAdapter.onItemClickListner {

    lateinit var employeesAdapter: EmployeesAdapter
    override fun updateItem(emp: Employee) {

        (activity as HomeActivity).loadEmployee(emp)

    }

    override fun itemClick(emp: Employee) {
    viewModel.deleteEmployee(emp)
        employeesAdapter.notifyDataSetChanged()
        viewModel.getEmployees()


    }

    companion object {
        fun newInstance() = EmployeeListFragment()


    }

    private lateinit var viewModel: EmployeeViewModel
    private lateinit var binding: FragmentEmployeeListBinding

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_employee_list, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel::class.java)

        binding.addEmpClickListener = View.OnClickListener {
            (activity as HomeActivity).loadEmployee(Employee())
        }






        viewModel.getEmployees()

        viewModel.employees
            .observe(this, Observer<List<Employee>> { employees ->
                if (employees.isNotEmpty()) {
                    context?.let {
                        employeesAdapter= EmployeesAdapter(employees,it,this)
                        binding.rcvEmployees.adapter = employeesAdapter
                    }
                }
            })

    }

    interface NavigateToAddEmployee {
        fun loadEmployee(emp:Employee)
    }

}
