package com.luv2code.springboot.cruddemo.controller;


import com.luv2code.springboot.cruddemo.entity.Employee;
import com.luv2code.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {


    private EmployeeService employeeService;

    @Autowired
    EmployeeController(EmployeeService theEmployeeserver){
        employeeService=theEmployeeserver;
    }


    @GetMapping("/list")
    public String employeeList(Model theModel){
        List<Employee> emp=employeeService.findAll();
        theModel.addAttribute("employee",emp);
        return "employee/list-employee";
    }

    @GetMapping("/showFormAdd")
    public String addEmployee(Model theModel){
        Employee emp=new Employee();
        theModel.addAttribute("employee",emp);

        return "employee/emp-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmp){
        employeeService.save(theEmp);
        return "redirect:/employee/list";
    }

    @GetMapping("/showFormUpdate")
    public String updateEmployee(@RequestParam("employeeId") int empId, Model theModel){
        Employee emp=employeeService.findById(empId);
        theModel.addAttribute("employee",emp);
        return "employee/emp-form";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int empId){
        employeeService.deleteById(empId);
        return "redirect:/employee/list";
    }
}
