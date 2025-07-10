package com.example.gestorCitas.service;

import com.example.gestorCitas.domain.Department;

import java.util.List;

public interface DepartmentService {
    List<Department> getDepartmentList();
    Department getDepartmentById(int id);
    Department getDepartmentFindByName(String nameDepartment);
    void saveDepartment(Department department,int idInstitution);
    void updateDepartment(Department department);
    void deleteDepartment(int id);

    //list<Department) getAllDepartmentByIdInstitution(int idInstitution);
}
