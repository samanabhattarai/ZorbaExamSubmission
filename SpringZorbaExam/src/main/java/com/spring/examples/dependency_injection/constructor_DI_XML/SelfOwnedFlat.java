package com.spring.examples.dependency_injection.constructor_DI_XML;


public class SelfOwnedFlat {


     private SelfOwnedOneBHKFlat selfOwnedOneBHKFlat;
     private SelfOwnedTwoBHKFlat selfOwnedTwoBHKFlat;
     private SelfOwnedThreeBHKFlat selfOwnedThreeBHKFlat;

     private float length;
     private float height;
     private float breadth;

    public SelfOwnedFlat(float length, float breadth, float height, SelfOwnedOneBHKFlat selfOwnedOneBHKFlat, SelfOwnedTwoBHKFlat selfOwnedTwoBHKFlat, SelfOwnedThreeBHKFlat selfOwnedThreeBHKFlat) {
        this.selfOwnedOneBHKFlat = selfOwnedOneBHKFlat;
        this.selfOwnedTwoBHKFlat = selfOwnedTwoBHKFlat;
        this.selfOwnedThreeBHKFlat = selfOwnedThreeBHKFlat;
        this.length = length;
        this.breadth = breadth;
        this.height = height;
    }

    public SelfOwnedOneBHKFlat getSelfOwnedOneBHKFlat() {
        return selfOwnedOneBHKFlat;
    }

    public void setSelfOwnedOneBHKFlat(SelfOwnedOneBHKFlat selfOwnedOneBHKFlat) {
        this.selfOwnedOneBHKFlat = selfOwnedOneBHKFlat;
    }

    public SelfOwnedTwoBHKFlat getSelfOwnedTwoBHKFlat() {
        return selfOwnedTwoBHKFlat;
    }

    public void setSelfOwnedTwoBHKFlat(SelfOwnedTwoBHKFlat selfOwnedTwoBHKFlat) {
        this.selfOwnedTwoBHKFlat = selfOwnedTwoBHKFlat;
    }

    public SelfOwnedThreeBHKFlat getSelfOwnedThreeBHKFlat() {
        return selfOwnedThreeBHKFlat;
    }

    public void setSelfOwnedThreeBHKFlat(SelfOwnedThreeBHKFlat selfOwnedThreeBHKFlat) {
        this.selfOwnedThreeBHKFlat = selfOwnedThreeBHKFlat;
    }


    @Override
    public String toString() {
        return "SelfOwnedFlat{" +
                "selfOwnedOneBHKFlat=" + selfOwnedOneBHKFlat +
                ", selfOwnedTwoBHKFlat=" + selfOwnedTwoBHKFlat +
                ", selfOwnedThreeBHKFlat=" + selfOwnedThreeBHKFlat +
                '}';
    }

    public void finalPriceOfFlat() {
        selfOwnedOneBHKFlat.finalPriceOfFlat(getLength(), getBreadth(), getHeight());
        selfOwnedTwoBHKFlat.finalPriceOfFlat(getLength(), getBreadth(), getHeight());
        selfOwnedThreeBHKFlat.finalPriceOfFlat(getLength(), getBreadth(), getHeight());
    }

    public float getLength() {
        return length;
    }


    public float getHeight() {
        return height;
    }


    public float getBreadth() {
        return breadth;
    }

}



