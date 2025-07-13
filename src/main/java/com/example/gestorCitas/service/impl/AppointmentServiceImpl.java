package com.example.gestorCitas.service.impl;

import com.example.gestorCitas.domain.Appointment;
import com.example.gestorCitas.domain.Department;
import com.example.gestorCitas.domain.Employee;
import com.example.gestorCitas.domain.Vacant;
import com.example.gestorCitas.exception.ResponseRuntimeException;
import com.example.gestorCitas.projectionInterface.AppointmentProjection;
import com.example.gestorCitas.repository.AppointmentRepository;
import com.example.gestorCitas.repository.DepartmentRepository;
import com.example.gestorCitas.repository.EmployeeRepository;
import com.example.gestorCitas.repository.VacantRepository;
import com.example.gestorCitas.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    VacantRepository vacantRepository;

    private Appointment findByIdAppointment(int id){
        return appointmentRepository.findById(id).orElseThrow(
                ()->new ResponseRuntimeException("the appointment is not available", HttpStatus.NOT_FOUND)
        );
    }

    private Department findByIdDepartment(int idDepartment){
        return departmentRepository.findById(idDepartment).orElseThrow(
                ()->new ResponseRuntimeException("the apartment is not available", HttpStatus.NOT_FOUND)
        );
    }

    private Employee findByIdEmployee(int idEmployee){
        return employeeRepository.findById(idEmployee).orElseThrow(
                ()-> new ResponseRuntimeException("The employee is not available", HttpStatus.NOT_FOUND)
        );
    }

    private Vacant findByIdVacant(int idVacant){
        return vacantRepository.findById(idVacant).orElseThrow(
                ()-> new ResponseRuntimeException("The vacant is not available",HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public List<Appointment> getAppointmentList() {
        return appointmentRepository.findAll();
    }

    // specification
    @Override
    public List<AppointmentProjection> getAppointmentListByCriteria(Integer idEmployee,
                                                                    Integer idDepartment,
                                                                    Integer idVacant,
                                                                    LocalDate date) {
        if(idEmployee != null && idDepartment == null && idVacant == null && date ==null){
            Employee employee = findByIdEmployee(idEmployee);
            return appointmentRepository.findByEmployee(employee);
        }
        if(idEmployee == null && idDepartment != null && idVacant == null && date ==null){
            Department department = findByIdDepartment(idDepartment);
            return appointmentRepository.findByDepartment(department);
        }
        if(idEmployee == null && idDepartment == null && idVacant != null && date ==null){
            Vacant vacant = findByIdVacant(idVacant);
            return appointmentRepository.findByVacant(vacant);
        }
        if(idEmployee != null && idDepartment == null && idVacant == null && date !=null){
            Employee employee = findByIdEmployee(idEmployee);
            return appointmentRepository.findByDateAppointmentAndEmployee(date,employee);
        }
        if(idEmployee == null && idDepartment != null && idVacant == null && date !=null){
            Department department = findByIdDepartment(idDepartment);
            return appointmentRepository.findByDateAppointmentAndDepartment(date, department);
        }

        throw new ResponseRuntimeException("no data has been entered", HttpStatus.BAD_REQUEST);
    }

    @Override
    public void saveAppointment(Appointment appointment, int idEmployee, int idVacant) {
        Employee employee = findByIdEmployee(idEmployee);
        Vacant vacant = findByIdVacant(idVacant);
        Department department = vacant.getDepartment();

        if(appointmentRepository.existsByEmployeeAndVacant(employee,vacant)){
            throw new ResponseRuntimeException("There is already an appointment with the vacancy " +
                    vacant.getNameVacant(), HttpStatus.CONFLICT);
        }
        if(appointmentRepository.existsByEmployeeAndTimeAppointment(employee, appointment.getTimeAppointment())){
            throw  new ResponseRuntimeException("There is already an appointment with the time " +
                    appointment.getTimeAppointment(), HttpStatus.CONFLICT);
        }

        appointment.setEmployee(employee);
        appointment.setVacant(vacant);
        appointment.setDepartment(department);
        appointmentRepository.save(appointment);
    }

    @Override
    public void updateAppointment(Appointment appointment) {
        Appointment appointmentExist = findByIdAppointment(appointment.getIdAppointment());
        appointment.setEmployee(appointmentExist.getEmployee());
        appointment.setVacant(appointmentExist.getVacant());
        appointment.setDepartment(appointmentExist.getDepartment());
        appointmentRepository.save(appointment);
    }

    @Override
    public void deleteAppointment(int id) {
        Appointment appointmentDelete = findByIdAppointment(id);
        appointmentRepository.deleteById(id);

    }


}
