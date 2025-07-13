package com.example.gestorCitas.repository;

import com.example.gestorCitas.domain.Institution;
import com.example.gestorCitas.projectionInterface.InstitutionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution,Integer> {
    Optional<InstitutionProjection> findByIdInstitution(int id);
    Optional<InstitutionProjection> findInstitutionProjectionByNameInstitution(String name);
    Optional<Institution> findByNameInstitution(String name);
}
