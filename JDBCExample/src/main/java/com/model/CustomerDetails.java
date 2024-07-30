package com.model;

import java.time.LocalDateTime;

public class CustomerDetails {
    public int custId;
    public String custName;
    public String custLocation;
    public String custEmail;
    public Long custMobile;

    public int getCustId() {
        return custId;
    }

    public void setCustId(int custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustLocation() {
        return custLocation;
    }

    public void setCustLocation(String custLocation) {
        this.custLocation = custLocation;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public Long getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(Long custMobile) {
        this.custMobile = custMobile;
    }

    public LocalDateTime getvHireDate() {
        return vHireDate;
    }

    public void setvHireDate(LocalDateTime vHireDate) {
        this.vHireDate = vHireDate;
    }

    public LocalDateTime vHireDate;

    @Override
    public String toString() {
        return "CustomerDetails{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custLocation='" + custLocation + '\'' +
                ", custEmail='" + custEmail + '\'' +
                ", custMobile=" + custMobile +
                ", vHireDate=" + vHireDate +
                '}';
    }
}
