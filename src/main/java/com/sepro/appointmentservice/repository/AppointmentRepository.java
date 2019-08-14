package com.sepro.appointmentservice.repository;

import com.sepro.appointmentservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Optional<Appointment> findById(Long appointmentId);
    @Override
    Appointment getOne(Long aLong);

    @Override
    List<Appointment> findAll();
}
