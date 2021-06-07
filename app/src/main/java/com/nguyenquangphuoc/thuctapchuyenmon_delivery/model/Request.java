package com.nguyenquangphuoc.thuctapchuyenmon_delivery.model;

import java.util.List;

public class Request {
    private String phone,name,address,total,status;
    private List<Order> food; //list of food order
    public Request(){

    }

    public Request(String phone, String name, String address, String total, List<Order> food) {
        this.phone = phone;
        this.name = name;
        this.address = address;
        this.total = total;
        this.food = food;
        this.status = "0"; //def 0 , 1:Shiping, 2:shipped
    }

    public String getStatus() {
        return status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Order> getFood() {
        return food;
    }

    public void setFood(List<Order> food) {
        this.food = food;
    }
}
