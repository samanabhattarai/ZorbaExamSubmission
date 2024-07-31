package com.model;

import java.util.Scanner;

public class RailwayReservation {

    private String passengerName;
    private int passengerAge;
    private String choosenSeat;
    private String reservationType;
    private boolean isSeniorCitizen;
    private double amountPaid;


    public RailwayReservation(Scanner scanner) {

        System.out.println("Enter passenger name: ");
        passengerName = scanner.next();

        System.out.println("Enter passenegr age: ");
        while (!(scanner.hasNextInt())) {
            System.out.println("eneter the valid age type");
            scanner.nextLine();
        }

        passengerAge = scanner.nextInt();
        scanner.nextLine();

        if (passengerAge > 60) {
            isSeniorCitizen = true;
            choosenSeat = "berth/seat";
        } else {
            isSeniorCitizen = false;
            System.out.println("eneter choosen seat");
            choosenSeat = scanner.nextLine();

        }
        System.out.println("Enter reservation type Ac/NonAc: ");
        reservationType = scanner.nextLine();

        double rateType = reservationType.equals("Ac") ? 100 : 60;
        amountPaid = isSeniorCitizen ? rateType / 2 : rateType;

    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getChoosenSeat() {
        return choosenSeat;
    }

    public int getPassengerAge() {
        return passengerAge;
    }

    public String getReservationType() {
        return reservationType;
    }

    public boolean isSeniorCitizen() {
        return isSeniorCitizen;
    }

    public double getAmountPaid() {
        return amountPaid;
    }
}

