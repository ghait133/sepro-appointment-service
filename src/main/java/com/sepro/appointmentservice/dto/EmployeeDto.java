package com.sepro.appointmentservice.dto;

import com.sepro.appointmentservice.entity.BaseIdEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "employee")
public class EmployeeDto extends BaseIdEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String telNumbre;
    private String gender;
    private Date birthdate;

    private Long user_id;
    @OneToOne(targetEntity = AdresseDto.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "adresse", foreignKey = @ForeignKey(name = "id"))
    private AdresseDto adresse;

    @OneToOne(targetEntity = PartnerDto.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "partner", foreignKey = @ForeignKey(name = "id"))
    private PartnerDto  partner;

    public EmployeeDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNumbre() {
        return telNumbre;
    }

    public void setTelNumbre(String telNumbre) {
        this.telNumbre = telNumbre;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public AdresseDto getAdresse() {
        return adresse;
    }

    public void setAdresse(AdresseDto adresse) {
        this.adresse = adresse;
    }

    public PartnerDto getPartner() {
        return partner;
    }

    public void setPartner(PartnerDto partner) {
        this.partner = partner;
    }
}
