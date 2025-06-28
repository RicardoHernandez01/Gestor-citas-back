package com.example.gestorCitas.repository;

import com.example.gestorCitas.domain.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution,Integer> {

}
