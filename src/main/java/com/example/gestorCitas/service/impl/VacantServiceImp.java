package com.example.gestorCitas.service.impl;

import com.example.gestorCitas.domain.Department;
import com.example.gestorCitas.domain.Vacant;
import com.example.gestorCitas.exception.ResponseRuntimeException;
import com.example.gestorCitas.projectionInterface.VacantProjection;
import com.example.gestorCitas.repository.DepartmentRepository;
import com.example.gestorCitas.repository.VacantRepository;
import com.example.gestorCitas.service.VacantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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

    @Override
    public VacantProjection getVacantByNameVacant(String nameVacant) {
        return vacantRepository.findVacantProjectionByNameVacant(nameVacant).orElseThrow(
                () -> new ResponseRuntimeException("The vacancy is not available", HttpStatus.NOT_FOUND)
        );
    }


    @Override
    public List<VacantProjection> getVacantListBySpecification(String positionVacant, Integer idDepartment, String nameDepartment) {
       if(positionVacant !=null){
           return vacantRepository.findByPositionVacant(positionVacant);
       }
       if(idDepartment !=null){
           Department department = findByidDepartment(idDepartment);
           return  vacantRepository.findByDepartment(department);
       }
       if(nameDepartment != null){
           Department department = departmentRepository.findByNameDepartment(nameDepartment).orElseThrow(
                   () -> new ResponseRuntimeException("Department is not available", HttpStatus.NOT_FOUND)
           );
           return vacantRepository.findByDepartment(department);
       }
        throw new ResponseRuntimeException("no data has been received", HttpStatus.BAD_REQUEST);
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
