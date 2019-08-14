package com.sepro.appointmentservice.dto;

import com.sepro.appointmentservice.entity.BaseIdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "adresse")
public class AdresseDto extends BaseIdEntity {
    String streetName;
    String houseNumbre;
    String postCode;
    String cityName;
    String countryName;

    public AdresseDto() {
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumbre() {
        return houseNumbre;
    }

    public void setHouseNumbre(String houseNumbre) {
        this.houseNumbre = houseNumbre;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
