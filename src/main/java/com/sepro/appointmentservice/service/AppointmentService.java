package com.sepro.appointmentservice.service;

import com.sepro.appointmentservice.dto.AppointmentDto;
import com.sepro.appointmentservice.entity.Appointment;
import com.sepro.appointmentservice.repository.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AppointmentService {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    PartnerServiceRepository partnerServiceRepository;

    @Autowired
    OAuth2RestTemplate restTemplate;



    public void updateAppointment (AppointmentDto appointmentDto, Long appointmentId){

        // Getting the Appointment
        Appointment appointment = appointmentRepository.findById(appointmentId).get();

        // setting the Customer of this Appointment
        appointment.setCustomer(customerRepository.findById(appointmentDto.getCustomerId()).get());

        appointment.setPartner(partnerRepository.findById(appointmentDto.getPartnerId()).get());

        appointment.setEmployee(employeeRepository.findById(appointmentDto.getEmployeeId()).get());

        appointment.setPartnerService(partnerServiceRepository.findById(appointmentDto.getPartnerServiceId()).get());

        appointment.setDate(appointmentDto.getDate());

        appointment.setStart(appointmentDto.getStart());

        appointment.setEnd(appointmentDto.getEnd());
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getAllAppointmentsForPartner(String partnerEmail){

       return appointmentRepository.findByPartner(partnerRepository.findByEmail(partnerEmail));

    }

}
