package me.naloaty.vezdekodmobile.model;

import java.time.LocalTime;

public class Order {

    private String storeTitle;
    private String storeAddress;
    private LocalTime deliveryTime;

    public Order(String storeName, String storeAddress, LocalTime deliveryTime) {
        this.storeTitle = storeName;
        this.storeAddress = storeAddress;
        this.deliveryTime = deliveryTime;
    }

    public String getStoreTitle() {
        return storeTitle;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public LocalTime getDeliveryTime() {
        return deliveryTime;
    }
}
