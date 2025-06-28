package com.example.gestorCitas.repository;

import com.example.gestorCitas.domain.Vacant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacantRepository extends JpaRepository<Vacant,Integer> {
}
