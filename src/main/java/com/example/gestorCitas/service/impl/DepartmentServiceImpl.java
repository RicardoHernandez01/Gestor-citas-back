package com.example.gestorCitas.service.impl;

import com.example.gestorCitas.domain.Department;
import com.example.gestorCitas.domain.Institution;
import com.example.gestorCitas.exception.ResponseRuntimeException;
import com.example.gestorCitas.projectionInterface.DepartmentProjection;
import com.example.gestorCitas.repository.DepartmentRepository;
import com.example.gestorCitas.repository.InstitutionRepository;
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

    @Autowired
    InstitutionRepository institutionRepository;

    private Department findByIdDepartment(int id){
        return departmentRepository.findById(id).orElseThrow(
                ()-> new ResponseRuntimeException("the apartment is not available", HttpStatus.NOT_FOUND));
    }

    private Institution findByIdInstitution(int idInstitution){
        return institutionRepository.findById(idInstitution).orElseThrow(
                ()-> new ResponseRuntimeException("the institution is not available",HttpStatus.NOT_FOUND));
    }

    @Override
    public List<DepartmentProjection> getDepartmentListByInstitution(int idInstitution) {
        Institution institution = findByIdInstitution(idInstitution);
        return departmentRepository.findByInstitution(institution);
    }

    @Override
    public List<DepartmentProjection> getDepartmentListByNameInstitution(String nameInstitution) {
        Institution institution = institutionRepository.findByNameInstitution(nameInstitution).orElseThrow(
                () -> new ResponseRuntimeException("the name Institution is not available", HttpStatus.NOT_FOUND)
        );
        return departmentRepository.findByInstitution(institution);
    }

    @Override
    public DepartmentProjection getDepartmentById(int id) {
        return departmentRepository.findByIdDepartment(id).orElseThrow(
                () -> new ResponseRuntimeException("The apartment is not available", HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public DepartmentProjection getDepartmentFindByName(String nameDepartment) {
        return departmentRepository.findDepartmentProjectionByNameDepartment(nameDepartment).orElseThrow(
                ()->new ResponseRuntimeException("the apartment is not available", HttpStatus.NOT_FOUND));
    }

    @Override
    public void saveDepartment(Department department, int idInstitution) {
        Institution institutionExist = findByIdInstitution(idInstitution);
        if(department!=null && institutionExist!=null){
            department.setInstitution(institutionExist);
             departmentRepository.save(department);
        }else {
            throw new ResponseRuntimeException("the department is null",HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void updateDepartment(Department department) {
        Department departmentExist = findByIdDepartment(department.getIdDepartment());
        department.setInstitution(departmentExist.getInstitution());
         departmentRepository.save(department);
    }

    @Override
    public void deleteDepartment(int id) {
        Department departmentExist = findByIdDepartment(id);
        try{
            departmentRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new ResponseRuntimeException(e.getMessage(),HttpStatus.CONFLICT);
        } catch (Exception e){
            throw  new ResponseRuntimeException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
