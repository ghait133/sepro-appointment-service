package com.sepro.appointmentservice.repository;

import com.sepro.appointmentservice.dto.PartnerDto;
import com.sepro.appointmentservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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

    @Query("select t from appointment t where t.start = :starTime and t.end = :endTime")
    List<Appointment> findByTimeInterval(
            @Param("starTime") Long starTime,
            @Param("endTime") Long endTime);

}
