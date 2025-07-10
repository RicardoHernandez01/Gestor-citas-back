package com.example.gestorCitas.service.impl;

import com.example.gestorCitas.domain.Department;
import com.example.gestorCitas.domain.Vacant;
import com.example.gestorCitas.exception.ResponseRuntimeException;
import com.example.gestorCitas.repository.DepartmentRepository;
import com.example.gestorCitas.repository.VacantRepository;
import com.example.gestorCitas.service.VacantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacantServiceImp implements VacantService {

    @Autowired
    VacantRepository vacantRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    /**
     * Private method for searching a vacant for id
     * @param id the vacant
     * @return vacant
     */
    private Vacant findById(int id){
        return vacantRepository.findById(id).orElseThrow(
                ()-> new ResponseRuntimeException("the vacant is not available", HttpStatus.NOT_FOUND));
    }

    /**
     * method for searching a apartment for id
     * @param id the departmnet
     * @return department object
     */
    private Department findByidDepartment(int id){
        return departmentRepository.findById(id).orElseThrow(
                ()-> new ResponseRuntimeException("the apartment is not available", HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public List<Vacant> getVacantList() {
        return vacantRepository.findAll();
    }

    @Override
    public Vacant getVacantById(int id) {
        return findById(id);
    }

    /**
     * method to search for a vacancy by name
     * @param  vacant vacancy name
     * @return vacante object
     */
    @Override
    public Vacant getVacantByNameVacant(String vacant) {
        return  vacantRepository.findByNameVacant(vacant).orElseThrow(
                ()-> new ResponseRuntimeException("the vacant is not available",HttpStatus.NOT_FOUND));
    }

    /**
     * method to get a list of vacancies by position
     * @param position vacancy position
     * @return vacancy list
     */
    @Override
    public List<Vacant> getVacantByPositionVacant(String position) {
        List<Vacant> vacantList = vacantRepository.findByPositionVacant(position);
        if(vacantList!=null){
            return vacantList;
        }
        throw new ResponseRuntimeException("the vacant list is null",HttpStatus.BAD_REQUEST);
    }

    @Override
    public void saveVacant(Vacant vacant, int idDepartment) {
        Department departmentExist = findByidDepartment(idDepartment);
        vacant.setDepartment(departmentExist);
        try{
            vacantRepository.save(vacant);
        }catch (DataIntegrityViolationException e){
            throw new ResponseRuntimeException(e.getMessage(),HttpStatus.CONFLICT);
        } catch (Exception e){
            throw new ResponseRuntimeException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void updateVacant(Vacant vacant) {
        Vacant vacantExist = findById(vacant.getIdVacant());
        vacant.setDepartment(vacantExist.getDepartment());
        try{
             vacantRepository.save(vacant);
        }catch (DataIntegrityViolationException e){
            throw new ResponseRuntimeException(e.getMessage(),HttpStatus.CONFLICT);
        } catch (Exception e){
            throw new ResponseRuntimeException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public void deleteVacant(int id) {
        Vacant vacantExist = findById(id);
        try {
            vacantRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new ResponseRuntimeException(e.getMessage(),HttpStatus.CONFLICT);
        }catch (Exception e){
            throw new ResponseRuntimeException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
