package com.example.gestorCitas.service;

import com.example.gestorCitas.domain.Institution;
import com.example.gestorCitas.projectionInterface.InstitutionProjection;

import java.util.List;

public interface InstitutionService {
    List<Institution> getInstitutionList();
    Institution saveInstitution(Institution institution);
    Institution updateInstitution(int id, Institution institution);
    void deleteInstitution(int idInstitution);
    InstitutionProjection findByInstitutionCriteria(Integer idInstitution, String nameInstitution);
}
