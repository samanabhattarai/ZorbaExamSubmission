package com.spring.examples.dependency_injection.constructor_DI_XML;

public class RentOfFlat {

    private RentOfOneBHKFlat rentOfOneBHKFlat;
    private RentOfTwoBHKFlat rentOfTwoBHKFlat;
    private RentOfThreeBHKFlat rentOfThreeBHKFlat;

    private int noOfPerson;

    public RentOfFlat(RentOfOneBHKFlat rentOfOneBHKFlat, RentOfTwoBHKFlat rentOfTwoBHKFlat, RentOfThreeBHKFlat rentOfThreeBHKFlat) {
        this.rentOfOneBHKFlat = rentOfOneBHKFlat;
        this.rentOfTwoBHKFlat = rentOfTwoBHKFlat;
        this.rentOfThreeBHKFlat = rentOfThreeBHKFlat;
    }

    @Override
    public String toString() {
        return "RentOfFlat{" +
                "rentOfOneBHKFlat=" + rentOfOneBHKFlat +
                ", rentOfTwoBHKFlat=" + rentOfTwoBHKFlat +
                ", rentOfThreeBHKFlat=" + rentOfThreeBHKFlat +
                '}';
    }

    public int getNoOfPerson() {
        return noOfPerson;
    }

    public void setNoOfPerson(int noOfPerson) {
        this.noOfPerson = noOfPerson;
    }

    public void calculateRentOfFlat() {
        rentOfOneBHKFlat.calculateRentOfFlat(getNoOfPerson());
        rentOfTwoBHKFlat.calculateRentOfFlat(getNoOfPerson());
        rentOfThreeBHKFlat.calculateRentOfFlat(getNoOfPerson());
    }
}