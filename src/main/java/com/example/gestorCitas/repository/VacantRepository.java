package com.example.gestorCitas.repository;

import com.example.gestorCitas.domain.Department;
import com.example.gestorCitas.domain.Vacant;
import com.example.gestorCitas.projectionInterface.VacantProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacantRepository extends JpaRepository<Vacant,Integer> {
    Optional<VacantProjection> findVacantProjectionByNameVacant(String nameVacant);
    List<VacantProjection> findByPositionVacant(String positionVacant);
    List<VacantProjection> findByDepartment(Department department);
}
