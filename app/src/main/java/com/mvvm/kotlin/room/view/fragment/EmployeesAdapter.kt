package com.mvvm.kotlin.room.view.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.kotlin.R
import com.mvvm.kotlin.databinding.ItemEmployeeBinding
import com.mvvm.kotlin.room.model.Employee

class EmployeesAdapter(val emps: List<Employee>,val context:Context, val itemClick: onItemClickListner) :
    RecyclerView.Adapter<EmployeesAdapter.EmpViewHolder>() {
  //  var itemClick: onItemClickListner=onitemclick
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmpViewHolder {
        val binding = DataBindingUtil.inflate<ItemEmployeeBinding>(
            LayoutInflater.from(context),
            R.layout.item_employee,
            parent,
            false
        )
        return EmpViewHolder(binding)
    }

    override fun getItemCount() = emps.size

    override fun onBindViewHolder(holder: EmpViewHolder, position: Int) {
        holder.bind(emps[position],position)
    }

    inner class EmpViewHolder(val binding: ItemEmployeeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(obj: Any,position: Int) {
            binding.employee = obj as Employee
            binding.executePendingBindings()

            binding.deleteBtn.setOnClickListener {
                Toast.makeText(context, "Deleted CLICKED", Toast.LENGTH_SHORT).show()
                itemClick.itemClick(emps.get(position))
                
            }

            binding.editBtn.setOnClickListener {
                Toast.makeText(context, "Edit CLICKED", Toast.LENGTH_SHORT).show()
                itemClick.updateItem(emps.get(position))
           }


        }
    }

interface onItemClickListner{
    fun itemClick(emp :Employee)
    fun updateItem(emp: Employee)

    
}
}