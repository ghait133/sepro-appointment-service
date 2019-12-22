package com.sepro.appointmentservice.dto;

import com.sepro.appointmentservice.entity.BaseIdEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Duration;

@Entity
@Table(name = "partner_service")
public class PartnerServicesDto extends BaseIdEntity {
    private Long partner_id;
    private Long systemServiceId;
    private String label;
    private String secondeLabel;
    private String description;
    private Long duration;
    private double price;
    private boolean enabled;

    public PartnerServicesDto() {
    }

    public Long getPartner_id() {
        return partner_id;
    }

    public void setPartner_id(Long partner_id) {
        this.partner_id = partner_id;
    }

    public Long getSystemServiceId() {
        return systemServiceId;
    }

    public void setSystemServiceId(Long systemServiceId) {
        this.systemServiceId = systemServiceId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getSecondeLabel() {
        return secondeLabel;
    }

    public void setSecondeLabel(String secondeLabel) {
        this.secondeLabel = secondeLabel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
