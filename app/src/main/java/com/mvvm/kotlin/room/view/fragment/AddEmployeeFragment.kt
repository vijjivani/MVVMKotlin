package com.mvvm.kotlin.room.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.mvvm.kotlin.R
import com.mvvm.kotlin.databinding.FragmentAddEmployeeBinding

import com.mvvm.kotlin.room.model.Employee
import com.mvvm.kotlin.room.view.HomeActivity


class AddEmployeeFragment : Fragment() {
    lateinit var myObject: Employee

    companion object {


        private val ARG_PARAM = "myObject"


        fun newInstance(myObject: Employee): AddEmployeeFragment {
            val fragment = AddEmployeeFragment()
            val args = Bundle()
            args.putSerializable(ARG_PARAM, myObject)
            fragment.arguments = args
            return fragment
        }

    }

    private lateinit var binding: FragmentAddEmployeeBinding
    private lateinit var viewModel: EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_employee, container, false)
        return binding.root
    }

    fun showToast(msg: String) {
        Toast.makeText(context!!, msg, Toast.LENGTH_SHORT).show()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel::class.java)

        if (arguments != null) {
            myObject = arguments?.getSerializable(ARG_PARAM) as Employee
            binding.editName.setText(myObject.name)
            binding.editName.setText(myObject.email)
            binding.editName.setText(myObject.phone)

        }


        viewModel.employeeSaveResult
            .observe(this, Observer<Long> {
                if (!it.equals(-1)) {
                    showToast("Employee Added Successfully")
                    (activity as HomeActivity).loadEmployeeList()
                }
            })
        viewModel.employeeUpdateResult
            .observe(this, Observer<Int> {
                if (!it.equals(-1)) {
                    showToast("Employee Updated Successfully")
                    (activity as HomeActivity).loadEmployeeList()
                }
            })


        binding.saveEmployeeClick = View.OnClickListener {
            val name = binding.editName.text.toString()

            if (!viewModel.isValidName(name)) {
                showToast("Enter Valid Name")
                return@OnClickListener
            }

            val email = binding.editEmail.text.toString()

            if (!viewModel.isValidEmail(email)) {
                showToast("Enter Valid Email")
                return@OnClickListener
            }

            val mobile = binding.editMobileNo.text.toString()

            if (!viewModel.isValidPhoneNo(mobile)) {
                showToast("Enter Valid Mobile")
                return@OnClickListener
            }


            if (myObject.name != null) {
                viewModel.updateEmployee(
                    Employee(
                        roll_no = myObject.roll_no,
                        name = name,
                        email = email,
                        phone = mobile
                    )
                )
            } else {
                viewModel.addEmployee(Employee(name = name, email = email, phone = mobile))

            }
        }


    }

    interface NavigateToEmployeeList {
        fun loadEmployeeList()
    }

}
