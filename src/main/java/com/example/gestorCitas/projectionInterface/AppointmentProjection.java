package com.example.gestorCitas.projectionInterface;

import java.time.LocalDate;
import java.time.LocalTime;

public interface AppointmentProjection {

    LocalDate getDateAppointment();
    LocalTime getTimeAppointment();

    VacantProjection getVacant();
}
