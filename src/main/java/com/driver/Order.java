package com.driver;

public class Order {

    private String id;
    private int deliveryTime;

    public Order(String id, String deliveryTime) {

        String str[]=deliveryTime.split(":");
        int hh=Integer.parseInt(str[0]);
        int mm=Integer.parseInt(str[1]);

        int totalTime=hh*60+mm;
        this.deliveryTime=totalTime;

        this.id=id;
        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public Order(){}

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}


    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(String deliveryTime) {
        String[] str = deliveryTime.split(":");
        this.deliveryTime = Integer.parseInt(str[0]) * 60 + Integer.parseInt(str[1]);
    }
}
