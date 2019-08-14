package com.sepro.appointmentservice.repository;

import com.sepro.appointmentservice.dto.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<CustomerDto, Long> {

    CustomerDto findByEmail(String email);
    Optional<CustomerDto> findById(Long customerId);
}
