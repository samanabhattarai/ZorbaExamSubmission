package com.spring.examples.dependency_injection.constructor_DI_XML;

public class SelfOwnedTwoBHKFlat {

    public float calculatePlot(float length, float breadth, float height) {
        return length * breadth * height;
    }

    public float finalPriceOfFlat(float length, float breadth, float height) {
        float price =  30000 * calculatePlot(length, breadth, height);
        System.out.println("Price of self owned Two BHK flat for length="+ length +" breadth="+breadth+" height="+height+" is = "+ price);
        return price;
    }

}
