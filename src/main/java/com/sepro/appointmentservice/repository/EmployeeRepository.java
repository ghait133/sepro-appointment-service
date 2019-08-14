package com.sepro.appointmentservice.repository;

import com.sepro.appointmentservice.dto.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeDto, Long> {

    Optional<EmployeeDto> findById(Long EmployeeId);
}
