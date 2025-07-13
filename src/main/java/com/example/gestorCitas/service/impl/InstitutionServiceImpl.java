package com.example.gestorCitas.service.impl;

import com.example.gestorCitas.domain.Institution;
import com.example.gestorCitas.exception.ResponseRuntimeException;
import com.example.gestorCitas.projectionInterface.InstitutionProjection;
import com.example.gestorCitas.repository.InstitutionRepository;
import com.example.gestorCitas.service.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class InstitutionServiceImpl implements InstitutionService {

    @Autowired
    InstitutionRepository institutionRepository;

    private Institution findById(int id){
        return institutionRepository.findById(id).orElseThrow(
                ()-> new ResponseRuntimeException("Institution is not available",HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Institution> getInstitutionList() {
        List<Institution> institutionList = institutionRepository.findAll();
        if(institutionList.isEmpty()){
            throw new ResponseRuntimeException("the institution list is empty", HttpStatus.NOT_FOUND);
        }
        return institutionList;
    }

    @Override
    public Institution saveInstitution(Institution institution) {
        if(institution!= null){
            return institutionRepository.save(institution);
        }
        throw new ResponseRuntimeException("Error saving institution", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public Institution updateInstitution(int id, Institution institution) {
        Institution institutionExist = findById(id);
        institution.setIdInstitution(id);
        return institutionRepository.save(institution);
    }

    @Override
    public void deleteInstitution(int idInstitution) {
        Institution institutionExist = findById(idInstitution);
        try{
            institutionRepository.deleteById(idInstitution);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseRuntimeException(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e){
            throw new ResponseRuntimeException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    public InstitutionProjection findByInstitutionCriteria(
            Integer idInstitution,
            String nameInstitution) {
        if(idInstitution != null){
            return institutionRepository.findByIdInstitution(idInstitution).orElseThrow(
                    () -> new ResponseRuntimeException("Institution is not available", HttpStatus.NOT_FOUND)
            );
        }
        if(nameInstitution !=null){
            return institutionRepository.findInstitutionProjectionByNameInstitution(nameInstitution).orElseThrow(
                    () -> new ResponseRuntimeException("Institution is not available", HttpStatus.NOT_FOUND)
            );
        }

        throw new ResponseRuntimeException("no data has been received",HttpStatus.BAD_REQUEST);
    }

}
