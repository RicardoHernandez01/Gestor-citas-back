package com.example.gestorCitas.repository;

import com.example.gestorCitas.domain.Department;
import com.example.gestorCitas.domain.Institution;
import com.example.gestorCitas.projectionInterface.DepartmentProjection;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DepartmentRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    InstitutionRepository institutionRepository;

    private Department findByIdDepartment(int id){
        return departmentRepository.findById(id).get();
    }

    private Institution findByIdInstitution(int idInstitution){
        return institutionRepository.findById(idInstitution).get();
    }

    @Test
    @Transactional
    public void findDepartmentListByInstitution(){
        Institution institution = findByIdInstitution(1);
        List<DepartmentProjection> departmentList = departmentRepository.findByInstitution(institution);
        System.out.println(departmentList);
    }

    @Test
    public void findDepartmentByNamDepartment(){
        Department department = departmentRepository.findByNameDepartment("cobao 2").get();
        System.out.println(department.toString());
    }


}