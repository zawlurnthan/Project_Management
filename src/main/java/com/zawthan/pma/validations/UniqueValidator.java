package com.zawthan.pma.validations;

import com.zawthan.pma.models.Employee;
import com.zawthan.pma.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String> {

    @Autowired
    EmployeeRepository empRepo;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){

        System.out.println("Enter validation method...");

        Employee emp = empRepo.findByEmail(value);

        if(emp != null)
            return false;
        else
            return true;
    }
}
