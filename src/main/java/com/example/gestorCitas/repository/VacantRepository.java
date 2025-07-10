package com.example.gestorCitas.repository;

import com.example.gestorCitas.domain.Vacant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacantRepository extends JpaRepository<Vacant,Integer> {
    Optional<Vacant> findByNameVacant(String name);
    List<Vacant> findByPositionVacant(String positionVacant);
    //List<Vacant> findByNameDepartment(String nameDepartment)
}
