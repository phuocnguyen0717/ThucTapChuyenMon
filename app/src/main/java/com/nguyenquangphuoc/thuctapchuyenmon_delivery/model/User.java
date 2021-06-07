package com.nguyenquangphuoc.thuctapchuyenmon_delivery.model;

public class User {
    private String Name;
    private String Password;
    private String Address;
    private String Phone;

    public String getPhone() {
        return Phone;
    }

    public User() {
    }

    public User(String name, String password, String address) {
        Name = name;
        Password = password;
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }


}