package com.spring.examples.dependency_injection.constructor_DI_XML;

public class RentOfOneBHKFlat {

    public int calculateRentOfFlat(int noOfPerson) {
        int rent =  250 * noOfPerson;
        System.out.println("Rent Of One BHK Flat for "+ noOfPerson + " person is = "+ rent);
        return rent;
    }

}
