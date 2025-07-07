package com.example.gestorCitas.service.impl;

import com.example.gestorCitas.domain.Department;
import com.example.gestorCitas.exception.ResponseRuntimeException;
import com.example.gestorCitas.repository.DepartmentRepository;
import com.example.gestorCitas.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    private Department findById(int id){
        return departmentRepository.findById(id).orElseThrow(
                ()-> new ResponseRuntimeException("the apartment is not available", HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Department> getDepartmentList() {
        List<Department> departmentList = departmentRepository.findAll();
        if(departmentList.isEmpty()){
            throw new ResponseRuntimeException("the apartment list is empty", HttpStatus.NOT_FOUND);
        }
        return departmentList;
    }

    @Override
    public Department getDepartmentById(int id) {
        return findById(id);
    }

    @Override
    public Department getDepartmentFindByName(String nameDepartment) {
        return departmentRepository.findByNameDepartment(nameDepartment).orElseThrow(
                ()->new ResponseRuntimeException("the apartment is not available", HttpStatus.NOT_FOUND));
    }

    @Override
    public Department saveDepartment(Department department) {
        if(department!=null){
            return departmentRepository.save(department);
        }else {
            throw new ResponseRuntimeException("the department is null",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Department updateDepartment(Department department) {
        Department departmentExist = findById(department.getIdDepartment());
        return departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(int id) {
        Department departmentExist = findById(id);
        try{
            departmentRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new ResponseRuntimeException(e.getMessage(),HttpStatus.CONFLICT);
        } catch (Exception e){
            throw  new ResponseRuntimeException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
