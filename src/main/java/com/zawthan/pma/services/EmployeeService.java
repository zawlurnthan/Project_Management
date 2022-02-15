package com.zawthan.pma.services;

import com.zawthan.pma.models.Employee;
import com.zawthan.pma.repositories.EmployeeProject;
import com.zawthan.pma.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository empRepo;

    public Employee save(Employee employee){
        return empRepo.save(employee);
    }

    public Iterable<Employee> getAll(){
        return empRepo.findAll();
    }

    public Iterable<EmployeeProject> employeeProjects(){
        return empRepo.employeeProjects();
    }

    public Employee findByEmployeeId(long id){
        return empRepo.findByEmployeeId(id);
    }

    public void delete(Employee emp) {
        empRepo.delete(emp);
    }
}
