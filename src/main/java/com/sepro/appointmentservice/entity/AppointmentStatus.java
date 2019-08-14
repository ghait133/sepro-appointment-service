package com.sepro.appointmentservice.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "appointment_status")
public class AppointmentStatus extends BaseIdEntity implements Serializable {

    private static final long serialVersionUID = 1L;


    private String name;

    public AppointmentStatus() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
