package com.example.gestorCitas.service;

import com.example.gestorCitas.domain.Appointment;

import java.util.List;

public interface AppointmentService {

    List<Appointment> getAppointmentList();
    void saveAppointment(Appointment appointment, int idEmployee, int Vacant);
    void updateAppointment(Appointment appointment);
    void deleteAppointment(int id);
}
