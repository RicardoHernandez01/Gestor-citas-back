package com.example.gestorCitas.repository;

import com.example.gestorCitas.domain.Appointment;
import com.example.gestorCitas.domain.Employee;
import com.example.gestorCitas.domain.Vacant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    /*
    list<Appointment> findByVacant(int idVacant);
    List<Appointment> findByDepartment(int idDepartment);
    */
    List<Appointment> findByEmployee(Employee employee);

    /**
     * Valia si ya ya se ha agendado una cita para la vacante
     * @param employee
     * @param vacant
     * @return
     */
    boolean existsByEmployeeAndVacant(Employee employee, Vacant vacant);

    boolean existsByEmployeeAndTimeAppointment(Employee employee, LocalTime timeAppointment);

}
