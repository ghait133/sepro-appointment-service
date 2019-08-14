package com.sepro.appointmentservice.entity;


import com.sepro.appointmentservice.dto.CustomerDto;
import com.sepro.appointmentservice.dto.EmployeeDto;
import com.sepro.appointmentservice.dto.PartnerDto;
import com.sepro.appointmentservice.dto.PartnerServicesDto;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "appointment")
public class Appointment extends BaseIdEntity implements Serializable {
    private static final long serialVersionUID = 1L;


    @OneToOne(targetEntity = CustomerDto.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "customer", foreignKey = @ForeignKey(name = "id"))
    private CustomerDto customer;

    @OneToOne(targetEntity = PartnerDto.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "partner", foreignKey = @ForeignKey(name = "id"))
    private PartnerDto partner;


    @OneToOne(targetEntity = EmployeeDto.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "employee", foreignKey = @ForeignKey(name = "id"))
    private EmployeeDto employee;

    @OneToOne(targetEntity = PartnerServicesDto.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "partner_service", foreignKey = @ForeignKey(name = "id"))
    private PartnerServicesDto partnerService;

    @OneToOne(targetEntity = AppointmentStatus.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "status", foreignKey = @ForeignKey(name = "id"))
    private AppointmentStatus status;

    private Long appointmentInfoId;
    private LocalDate date;
    private LocalTime start;
    private LocalTime end;

    public Appointment() {
    }

    public PartnerServicesDto getPartnerService() {
        return partnerService;
    }

    public void setPartnerService(PartnerServicesDto partnerService) {
        this.partnerService = partnerService;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public PartnerDto getPartner() {
        return partner;
    }

    public void setPartner(PartnerDto partner) {
        this.partner = partner;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public Long getAppointmentInfoId() {
        return appointmentInfoId;
    }

    public void setAppointmentInfoId(Long appointmentInfoId) {
        this.appointmentInfoId = appointmentInfoId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }
}
