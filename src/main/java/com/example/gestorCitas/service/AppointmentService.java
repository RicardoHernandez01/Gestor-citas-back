package com.example.gestorCitas.service;

import com.example.gestorCitas.domain.Appointment;
import com.example.gestorCitas.projectionInterface.AppointmentProjection;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentService {

    List<Appointment> getAppointmentList();
    List<AppointmentProjection> getAppointmentListByCriteria(Integer idEmployee, Integer idDepartment, Integer idVacant, LocalDate date);
    void saveAppointment(Appointment appointment, int idEmployee, int Vacant);
    void updateAppointment(Appointment appointment);
    void deleteAppointment(int id);
}
