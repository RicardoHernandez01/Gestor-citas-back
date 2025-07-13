package com.example.gestorCitas.service;

import com.example.gestorCitas.domain.Vacant;
import com.example.gestorCitas.projectionInterface.VacantProjection;

import java.util.List;

public interface VacantService {

    List<Vacant> getVacantList();
    Vacant getVacantById(int id);
    VacantProjection getVacantByNameVacant(String nameVacant);
    List<VacantProjection> getVacantListBySpecification(String positionVacant, Integer idDepartment, String nameDepartment);
    void saveVacant(Vacant vacant, int idDepartment);
    void updateVacant(Vacant vacant);
    void deleteVacant(int id);

}
