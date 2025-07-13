package com.example.gestorCitas.repository;

import com.example.gestorCitas.domain.Department;
import com.example.gestorCitas.domain.Institution;
import com.example.gestorCitas.projectionInterface.DepartmentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    Optional<DepartmentProjection> findDepartmentProjectionByNameDepartment(String nameDepartment);
    Optional<Department> findByNameDepartment(String namDepartment);
    Optional<DepartmentProjection> findByIdDepartment(int idDepartment);
    List<DepartmentProjection> findByInstitution(Institution institution);

}
