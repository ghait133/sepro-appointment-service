package com.sepro.appointmentservice.dto;

import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.time.LocalTime;

public class AppointmentDto {

    private Long customerId;
    private Long partnerId;
    private Long employeeId;
    private Long partnerServiceId;
    private LocalDate date;
    private LocalTime end;
    private LocalTime start;

    public AppointmentDto(Long customerId, Long partnerId, Long employeeId, Long partnerServiceId, LocalDate date, LocalTime end, LocalTime start) {
        this.customerId = customerId;
        this.partnerId = partnerId;
        this.employeeId = employeeId;
        this.partnerServiceId = partnerServiceId;
        this.date = date;
        this.end = end;
        this.start = start;
    }
    public AppointmentDto(Long customerId, Long partnerId, Long employeeId, Long partnerServiceId) {
        this.customerId = customerId;
        this.partnerId = partnerId;
        this.employeeId = employeeId;
        this.partnerServiceId = partnerServiceId;
    }
    public AppointmentDto(AppointmentDto appointment, LocalDate date, LocalTime start, LocalTime end) {
        this.customerId = appointment.getCustomerId();
        this.partnerId = appointment.getPartnerId();
        this.employeeId = appointment.getEmployeeId();
        this.partnerServiceId = appointment.partnerServiceId;
        this.date = date;
        this.end = end;
        this.start = start;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(Long partnerId) {
        this.partnerId = partnerId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getPartnerServiceId() {
        return partnerServiceId;
    }

    public void setPartnerServiceId(Long partnerServiceId) {
        this.partnerServiceId = partnerServiceId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }
}
