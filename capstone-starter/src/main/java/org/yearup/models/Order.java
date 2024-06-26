package org.yearup.models;

public class Order
{
    private int orderId;
    private int userId;
    private String date;
    private String address;
    private String city;
    private String state;
    private String zip;
    private double shipping_amount;

    public Order
            (
            int orderId,
            int userId,
            String date, String address,
            String city, String state,
            String zip,
            double shipping_amount
            )
    {
        this.orderId = orderId;
        this.userId = userId;
        this.date = date;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.shipping_amount = shipping_amount;
    }

    public Order()
    {
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public double getShipping_amount() {
        return shipping_amount;
    }

    public void setShipping_amount(double shipping_amount) {
        this.shipping_amount = shipping_amount;
    }
}
