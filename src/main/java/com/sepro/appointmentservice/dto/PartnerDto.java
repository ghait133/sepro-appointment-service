package com.sepro.appointmentservice.dto;

import com.sepro.appointmentservice.entity.BaseIdEntity;

import javax.persistence.*;

@Entity
@Table(name = "partner")
public class PartnerDto extends BaseIdEntity {

    private String companyShortName;
    private String companyLongName;
    private String email;
    private String telNumber;
    @OneToOne(targetEntity = AdresseDto.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "adresse", foreignKey = @ForeignKey(name = "id"))
    private AdresseDto adresse;

    private Long user_id;
    private Long sector_id;

    public PartnerDto() {
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public String getCompanyLongName() {
        return companyLongName;
    }

    public void setCompanyLongName(String companyLongName) {
        this.companyLongName = companyLongName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public AdresseDto getAdresse() {
        return adresse;
    }

    public void setAdresse(AdresseDto adresse) {
        this.adresse = adresse;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getSector_id() {
        return sector_id;
    }

    public void setSector_id(Long sector_id) {
        this.sector_id = sector_id;
    }
}
