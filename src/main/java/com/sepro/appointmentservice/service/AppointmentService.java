package com.sepro.appointmentservice.service;

import com.sepro.appointmentservice.dto.AppointmentDto;
import com.sepro.appointmentservice.dto.EmployeeDto;
import com.sepro.appointmentservice.entity.Appointment;
import com.sepro.appointmentservice.repository.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.time.temporal.ChronoUnit.MINUTES;

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
    public Optional<Appointment> getOneAppointment (Long i){
        return appointmentRepository.findById(i);
    }
    public List<Appointment> getAllAppointmentsForPartner(String partnerEmail){

       return appointmentRepository.findByPartner(partnerRepository.findByEmail(partnerEmail));

    }

    public List<AppointmentDto> findAppointmentsforTimeInterval (AppointmentDto appointmentDto, LocalTime startDate, LocalTime endDate, LocalDate date){


        // TODO get Service duration
        Duration duration  = Duration.ofSeconds(partnerServiceRepository.findById(appointmentDto.getPartnerServiceId()).get().getDuration());

        // TODO defaultDurationBetweentowAppointments ist die aus der kürzeste Dienstleistung und Ihre Häufigkeit am tag zusammen zu setzen
        int defaultDurationBetweentowAppointments = 15;
        //endDate = endDate.minus(duration);
        EmployeeDto employeeDto = employeeRepository.findById(appointmentDto.getEmployeeId()).get();
        List<Appointment> exestingAppointment = appointmentRepository.findByTimeInterval(
                startDate.minusMinutes(defaultDurationBetweentowAppointments),
                endDate.plusMinutes(defaultDurationBetweentowAppointments),
                date,
                employeeDto);
        Collections.sort(exestingAppointment);

        List<AppointmentDto> freeAppointments = new ArrayList();
        int y = 0;
        Long freeTimeInterval = Long.valueOf(0);

        LocalTime e = endDate;

        if (exestingAppointment.isEmpty()){
            freeTimeInterval = MINUTES.between(startDate, e);
            if ( freeTimeInterval >= duration.toMinutes()){
                int x = (int) (freeTimeInterval / defaultDurationBetweentowAppointments);
                for (int j = 0; j <  x ; j++){
                    freeTimeInterval  = MINUTES.between(startDate.plusMinutes(j * defaultDurationBetweentowAppointments), e);
                    if (freeTimeInterval >= duration.toMinutes() ){
                        AppointmentDto appointment_1 = new AppointmentDto(appointmentDto,date,startDate.plusMinutes(j * defaultDurationBetweentowAppointments),(startDate.plusMinutes(duration.toMinutes())).plusMinutes(j * defaultDurationBetweentowAppointments));
                        freeAppointments.add(appointment_1);
                    }

                }
            }
        } else {
            if (exestingAppointment.get(0).getStart().isBefore(startDate)){
                startDate = exestingAppointment.get(0).getEnd();
                y = 1;
            }
            for (int i = y; i < exestingAppointment.size(); i++) {
                e = exestingAppointment.get(i).getStart();
                if (i == y) {
                    freeTimeInterval = MINUTES.between(startDate, e);
                } else {
                    startDate = exestingAppointment.get(i - 1).getEnd();
                    freeTimeInterval = MINUTES.between(startDate, e);
                }
                if (i == exestingAppointment.size() - 1) {
                    if (exestingAppointment.get(i).getEnd().isBefore(endDate)) {
                        startDate = exestingAppointment.get(i).getEnd();
                        e = endDate;
                        freeTimeInterval = MINUTES.between(startDate, e);
                    }
                }
                if (freeTimeInterval >= duration.toMinutes()) {
                    int x = (int) (freeTimeInterval / defaultDurationBetweentowAppointments);
                    if (x == 0) {
                        AppointmentDto appointment = new AppointmentDto(appointmentDto, date, startDate, startDate.plusMinutes(duration.toMinutes()));
                        freeAppointments.add(appointment);
                        continue;
                    }
                    for (int j = 0; j < x; j++) {
                        freeTimeInterval = MINUTES.between(startDate.plusMinutes(j * defaultDurationBetweentowAppointments), e);
                        if (freeTimeInterval >= duration.toMinutes()) {
                            AppointmentDto appointment_1 = new AppointmentDto(appointmentDto, date, startDate.plusMinutes(j * defaultDurationBetweentowAppointments), (startDate.plusMinutes(duration.toMinutes())).plusMinutes(j * defaultDurationBetweentowAppointments));
                            freeAppointments.add(appointment_1);
                        }

                    }
                }

            }
        }
        return freeAppointments;
    }

}
