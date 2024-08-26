package com.spring.examples.dependency_injection.constructor_DI_XML;

public class RentOfTwoBHKFlat {

    private Balcony balcony;

    public RentOfTwoBHKFlat(Balcony balcony) {
        this.balcony = balcony;
    }

    public int  calculateRentOfFlat(int noOfPerson) {
        int rent =  400 * noOfPerson;
        System.out.println("Rent Of Two BHK Flat with Balcony for "+ noOfPerson + " person is = "+ rent);
        return rent;
    }
}
