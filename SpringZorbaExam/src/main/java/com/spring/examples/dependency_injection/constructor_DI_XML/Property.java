package com.spring.examples.dependency_injection.constructor_DI_XML;

public class Property {
    private RentOfFlat rentOfFlat;
    private SelfOwnedFlat selfOwnedFlat;

    public Property(RentOfFlat rentOfFlat,SelfOwnedFlat selfOwnedFlat){
        this.rentOfFlat = rentOfFlat;
        this.selfOwnedFlat = selfOwnedFlat;
    }

    public void printDetails(){
        rentOfFlat.calculateRentOfFlat();
        selfOwnedFlat.finalPriceOfFlat();
    }

}
