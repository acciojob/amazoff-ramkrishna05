package com.driver;

// Order.java
public class Order {
    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {
        this.id = id;
        Integer time = settime(deliveryTime); // Check if deliveryTime format is correct
        this.deliveryTime = time;
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }

    // Helper method to convert HH:MM format delivery time to minutes
    private int settime(String deliveryTime) {
        String arr[] = deliveryTime.split(":");
        if (arr.length != 2) {
            throw new IllegalArgumentException("Invalid deliveryTime format. Use HH:MM.");
        }
        int hours = Integer.parseInt(arr[0]);
        int minutes = Integer.parseInt(arr[1]);
        if (hours < 0 || hours >= 24 || minutes < 0 || minutes >= 60) {
            throw new IllegalArgumentException("Invalid deliveryTime values.");
        }
        return hours * 60 + minutes;
    }
}
