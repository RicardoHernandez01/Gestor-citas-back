package com.example.gestorCitas.service;

import com.example.gestorCitas.domain.Vacant;

import java.util.List;

public interface VacantService {

    List<Vacant> getVacantList();
    Vacant getVacantById(int id);
    Vacant getVacantByNameVacant(String vacant);
    List<Vacant> getVacantByPositionVacant(String position);
    void saveVacant(Vacant vacant, int idDepartment);
    void updateVacant(Vacant vacant);
    void deleteVacant(int id);
    //List<Vacant> getVacantListByNameDepartment(String nameDepartment);
}
