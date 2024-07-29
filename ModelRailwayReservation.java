package com.jdbc.examples;

public class ModelRailwayReservation {
   private String passengerName;
   private int  passengerAge;
   private String choosenSeat;
   private String reservationType;
   private boolean isSeniorCitizen;
   private double amountPaid;

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public boolean isSeniorCitizen() {
        return isSeniorCitizen;
    }

    public void setSeniorCitizen(boolean seniorCitizen) {
        isSeniorCitizen = seniorCitizen;
    }

    public String getReservationType() {
        return reservationType;
    }

    public void setReservationType(String reservationType) {
        this.reservationType = reservationType;
    }

    public String getChoosenSeat() {
        return choosenSeat;
    }

    public void setChoosenSeat(String choosenSeat) {
        this.choosenSeat = choosenSeat;
    }

    public int getPassengerAge() {
        return passengerAge;
    }

    public void setPassengerAge(int passengerAge) {
        this.passengerAge = passengerAge;
    }

    @Override
    public String toString() {
        return "ModelRailwayReservation{" +
                "passengerName='" + passengerName + '\'' +
                ", passengerAge=" + passengerAge +
                ", choosenSeat='" + choosenSeat + '\'' +
                ", reservationType='" + reservationType + '\'' +
                ", isSeniorCitizen=" + isSeniorCitizen +
                ", amountPaid=" + amountPaid +
                '}';
    }
}
