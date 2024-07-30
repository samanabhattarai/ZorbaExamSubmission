package com.model;

import java.time.LocalDateTime;

public class VehicleDetails {

    public Integer vId;
    public String vName;
    public String vRegistration;
    public LocalDateTime vRegDate;
    private Integer customerId;

    public String getvName() {
        return vName;
    }

    public void setvName(String vName) {
        this.vName = vName;
    }

    public Integer getvId() {
        return vId;
    }

    public void setvId(Integer vId) {
        this.vId = vId;
    }

    public String getvRegistration() {
        return vRegistration;
    }

    public void setvRegistration(String vRegistration) {
        this.vRegistration = vRegistration;
    }

    public LocalDateTime getvRegDate() {
        return vRegDate;
    }

    public void setvRegDate(LocalDateTime vRegDate) {
        this.vRegDate = vRegDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "VehicleDetails{" +
                "vId=" + vId +
                ", vName='" + vName + '\'' +
                ", vRegistration='" + vRegistration + '\'' +
                ", vRegDate=" + vRegDate +
                ", customerId=" + customerId +
                '}';
    }
}
