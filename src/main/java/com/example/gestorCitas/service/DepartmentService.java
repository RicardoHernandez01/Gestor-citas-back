package com.example.gestorCitas.service;

import com.example.gestorCitas.domain.Department;
import com.example.gestorCitas.domain.Institution;
import com.example.gestorCitas.projectionInterface.DepartmentProjection;

import java.util.List;

public interface DepartmentService {
    List<DepartmentProjection> getDepartmentListByInstitution(int idInstitution);
    List<DepartmentProjection> getDepartmentListByNameInstitution(String nameInstitution);
    DepartmentProjection getDepartmentById(int id);
    DepartmentProjection getDepartmentFindByName(String nameDepartment);
    void saveDepartment(Department department,int idInstitution);
    void updateDepartment(Department department);
    void deleteDepartment(int id);

    //list<Department) getAllDepartmentByIdInstitution(int idInstitution);
}
