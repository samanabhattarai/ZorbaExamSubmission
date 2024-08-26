package com.spring.examples.dependency_injection.constructor_DI_XML;

public class RentOfThreeBHKFlat {
    private final Balcony balcony;

    public RentOfThreeBHKFlat(Balcony balcony) {
        this.balcony = balcony;
    }

    public int calculateRentOfFlat(int noOfPerson) {
        int rent =  600 * noOfPerson;
        System.out.println("Rent Of Three BHK Flat with Balcony for "+ noOfPerson + " person is = "+ rent);
        return rent;
    }

}