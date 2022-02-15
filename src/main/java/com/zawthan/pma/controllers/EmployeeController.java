package com.zawthan.pma.controllers;
import com.zawthan.pma.models.Employee;
import com.zawthan.pma.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
//import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService empService;

    @GetMapping
    public String displayEmployees(Model model){
        Iterable<Employee> employees = empService.getAll();
        model.addAttribute("employees", employees);
        return "list-employees";
    }

    @GetMapping("/new")
    public String displayEmployeeForm(Model model){
        model.addAttribute("employee", new Employee());
        return "new-employee";
    }

    @PostMapping("/save")
    public String createEmployee(@Valid Employee employee, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "/new-employee";
         //save project to Database.
        empService.save(employee);
        // use redirect to prevent duplicated submission.
        return "redirect:/employees";
    }

    @GetMapping("/update")
    public String updateEmployee(@RequestParam("id") long id, Model model){
        Employee emp = empService.findByEmployeeId(id);
        model.addAttribute("employee", emp);
        return "/new-employee";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") long id){
        Employee emp = empService.findByEmployeeId(id);
        empService.delete(emp);
        return "redirect:/employees";
    }
}
