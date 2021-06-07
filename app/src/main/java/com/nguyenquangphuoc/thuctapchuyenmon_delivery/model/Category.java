package com.nguyenquangphuoc.thuctapchuyenmon_delivery.model;

public class Category {
    private String NameMenu;
    private String Image;
    public Category() {
    }

    public Category(String nameMenu, String image) {
        NameMenu = nameMenu;
        Image = image;
    }

    public String getNameMenu() {
        return NameMenu;
    }

    public void setNameMenu(String nameMenu) {
        NameMenu = nameMenu;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }
}
