package com.sepro.appointmentservice.repository;

import com.sepro.appointmentservice.dto.PartnerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import sun.security.krb5.internal.PAData;

import java.util.Optional;

public interface PartnerRepository extends JpaRepository<PartnerDto, Long> {

    Optional<PartnerDto> findById(Long partnerId);
}
