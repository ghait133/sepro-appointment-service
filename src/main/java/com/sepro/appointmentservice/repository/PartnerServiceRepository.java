package com.sepro.appointmentservice.repository;

import com.sepro.appointmentservice.dto.PartnerServicesDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PartnerServiceRepository extends JpaRepository<PartnerServicesDto,Long> {

    Optional<PartnerServicesDto> findById(Long partnerserviceId);
}
