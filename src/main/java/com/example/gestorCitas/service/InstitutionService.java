package com.example.gestorCitas.service;

import com.example.gestorCitas.domain.Institution;

import java.util.List;

public interface InstitutionService {
    List<Institution> getInstitutionList();
    Institution saveInstitution(Institution institution);
    Institution UpdateInstitution(int id, Institution institution);
    void deleteInstitution(int idInstitution);

}
