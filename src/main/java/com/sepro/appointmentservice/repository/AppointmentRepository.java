package com.sepro.appointmentservice.repository;

import com.sepro.appointmentservice.dto.EmployeeDto;
import com.sepro.appointmentservice.dto.PartnerDto;
import com.sepro.appointmentservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Optional<Appointment> findById(Long appointmentId);

    List<Appointment> findByPartner(PartnerDto partner);
    @Override
    Appointment getOne(Long aLong);

    @Override
    List<Appointment> findAll();

    @Query("select t from Appointment t where t.end >= :starTime and t.start <= :endTime and t.date = :date and t.employee=:employee")
    List<Appointment> findByTimeInterval(
            @Param("starTime") LocalTime starTime,
            @Param("endTime") LocalTime endTime,
            @Param("date")LocalDate date,
            @Param("employee") EmployeeDto employee);

}
