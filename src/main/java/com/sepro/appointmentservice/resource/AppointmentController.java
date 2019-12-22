package com.sepro.appointmentservice.resource;

import com.sepro.appointmentservice.dto.AppointmentDto;
import com.sepro.appointmentservice.entity.Appointment;
import com.sepro.appointmentservice.model.CustomPrincipal;
import com.sepro.appointmentservice.repository.AppointmentRepository;
import com.sepro.appointmentservice.repository.CustomerRepository;
import com.sepro.appointmentservice.service.AppointmentService;
import com.sepro.appointmentservice.util.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.time.*;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@RestController
public class AppointmentController {
    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    AppointmentService appointmentService;

    @RequestMapping(value = "/{appointmentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Appointment> getcustomerInfobyId(@PathVariable Long appointmentId){
        //TODO show if Partner(Principal user) has an relationship to the customer in Customer_partner Table
        return appointmentRepository.findById(appointmentId);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Appointment> getAllAppointmentsforPartner(CustomPrincipal principal){
        // get partnerId
        return appointmentService.getAllAppointmentsForPartner(principal.getEmail());
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AppointmentDto> getOneAppointmentsforPartner(CustomPrincipal principal){
        // get partnerId
        LocalDate localdate_1 = appointmentService.getOneAppointment(Long.valueOf(5)).get().getDate();
        LocalDate localdate_2 = appointmentService.getOneAppointment(Long.valueOf(6)).get().getDate();
        LocalTime localTime_1 = appointmentService.getOneAppointment(Long.valueOf(5)).get().getStart();
        LocalTime localTime_2 = appointmentService.getOneAppointment(Long.valueOf(6)).get().getStart();
        LocalTime now = LocalTime.now();
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+2"));

        Timestamp timestamp_1 = Timestamp.valueOf(localdate_1.atTime(localTime_1));

        Timestamp timestamp_2 = Timestamp.valueOf(localdate_2.atTime(localTime_2));
        //return timestamp_1;
        ZonedDateTime zdt = timestamp_2.toLocalDateTime().atZone(ZoneId.of("Europe/Berlin"));
//Psudo
        LocalDate date = LocalDate.of(2018,9,13);
        LocalTime start = LocalTime.of(7,0);
        LocalTime end = LocalTime.of(20, 0);

        AppointmentDto appointmentDto = new AppointmentDto( Long.valueOf(161), Long.valueOf(59), Long.valueOf(4), Long.valueOf(5));

        //return appointmentDto;
        List<AppointmentDto> l = appointmentService.findAppointmentsforTimeInterval(appointmentDto,start,end,date);
        return l;


        //return zdt.toInstant().getEpochSecond();
    }


    @RequestMapping(value = "create" , method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public  GenericResponse createAppointment (
            @RequestBody final AppointmentDto appointmentDto){
        //TODO before create, confirme that the appointment  is free
        Appointment appointment = new Appointment();
        //TODO set partner, set date, set Employee
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
