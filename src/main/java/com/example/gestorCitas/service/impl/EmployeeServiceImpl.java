package com.example.gestorCitas.service.impl;

import com.example.gestorCitas.domain.Employee;
import com.example.gestorCitas.exception.ResponseRuntimeException;
import com.example.gestorCitas.repository.EmployeeRepository;
import com.example.gestorCitas.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    private Employee findById(int id){
        return employeeRepository.findById(id).orElseThrow(
                ()-> new ResponseRuntimeException("the Employee is not available", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Employee> getEmployeeList() {
        List<Employee> employeeList = employeeRepository.findAll();
        if(employeeList.isEmpty()){
            throw new ResponseRuntimeException("the employee list is empty",HttpStatus.NOT_FOUND);
        }
        return employeeList;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        if(employee!=null){
            return employeeRepository.save(employee);
        }
        throw new ResponseRuntimeException("Error saving institution",HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee employeeExist = findByIdEmployee(employee.getIdEmployee());
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        Employee employeeExist = findByIdEmployee(id);
        try {
            employeeRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new ResponseRuntimeException(e.getMessage(),HttpStatus.CONFLICT);
        }catch (Exception e){
            throw new ResponseRuntimeException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Employee findByIdEmployee(int id) {
        return findById(id);
    }

    @Override
    public Employee findByNameEmployee(String name) {
        return employeeRepository.findByFirstNameEmployee(name).orElseThrow(
                ()-> new ResponseRuntimeException("Employee is not available",HttpStatus.NOT_FOUND));
    }
}
