package com.sepro.appointmentservice.resource;

import com.sepro.appointmentservice.dto.AppointmentDto;
import com.sepro.appointmentservice.entity.Appointment;
import com.sepro.appointmentservice.repository.AppointmentRepository;
import com.sepro.appointmentservice.repository.CustomerRepository;
import com.sepro.appointmentservice.service.AppointmentService;
import com.sepro.appointmentservice.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@RestController
public class AppointmentController {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AppointmentService appointmentService;

    @RequestMapping(value = "/appointment/{appointmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Appointment> getcustomerInfobyId(@PathVariable Long appointmentId){
        //TODO show if Partner(Principal user) has an relationship to the customer in Customer_partner Table
        return appointmentRepository.findById(appointmentId);
    }

    @RequestMapping(value = "create" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  GenericResponse createAppointment (
            @RequestBody final AppointmentDto appointmentDto){
        Appointment appointment = new Appointment();
        appointment.setCustomer(customerRepository.findById(appointmentDto.getCustomerId()).get());

        appointmentRepository.save(appointment);
        return new GenericResponse("saved");
    }

    @RequestMapping(value = "update/{appointmentId}" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  GenericResponse updateppointment (
            @RequestBody final AppointmentDto appointmentDto, @PathVariable final Long appointmentId){

        appointmentService.updateAppointment(appointmentDto,appointmentId);

        return new GenericResponse("saved");
    }
}
