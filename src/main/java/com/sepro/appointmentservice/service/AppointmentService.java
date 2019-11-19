package com.sepro.appointmentservice.service;

import com.sepro.appointmentservice.dto.AppointmentDto;
import com.sepro.appointmentservice.entity.Appointment;
import com.sepro.appointmentservice.repository.*;

import java.time.Duration;
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

    public List<Appointment> findAppointmentsforTimeInterval (AppointmentDto appointmentDto, Long startDate, Long endDate){
        startDate = Long.valueOf(1572595200); // TODO get  the right start and end Times#
        endDate = Long.valueOf(1573840800);

        // TODO get Service duration
        Duration duration  = partnerServiceRepository.findById(appointmentDto.getPartnerServiceId()).get().getDuration();

        // TODO get all Appointment between start and end

        List<Appointment> exestingAppointment = appointmentRepository.findByTimeInterval(startDate, endDate);
        List<Appointment> freeAppointments;
         for (int i = 0 ;  i < exestingAppointment.size(); i++){
             if (i == 0 && exestingAppointment.get(i).getStart() - startDate >= duration){
                 //TODO create Appointments and put it in the List
                 for (int j = startDate; exestingAppointment.get(i).getStart() - startDate module 15min ; j+15min){
                     freeAppointments.add(createAppointment(appointmentDto, j));
                 }
             }
             if (exestingAppointment.get(i-1).getEnd() - exestingAppointment.get(i).getStart() >= duration){
                 for (int j = exestingAppointment.get(i-1).getEnd(); exestingAppointment.get(i).getStart() - exestingAppointment.get(i-1).getEnd() module 15min ; j+15min){
                     freeAppointments.add(createAppointment(appointmentDto, j));
                 }
             }
        }

    }

}
