package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        Integer time=settime(deliveryTime);
        this.deliveryTime=time;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
    public Integer settime(String deliveryTime)
    {
        String arr[]=deliveryTime.split(":");
        return Integer.parseInt(arr[0])*60+Integer.parseInt(arr[1]);
    }
}
