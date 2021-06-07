package com.nguyenquangphuoc.thuctapchuyenmon_delivery.model;

public class Food {
    private String NameFood, Image, Description, Price, DisCount, MenuId;

    public Food() {
    }

    public Food(String nameFood, String image, String description, String price, String disCount, String menuId) {
        NameFood = nameFood;
        Image = image;
        Description = description;
        Price = price;
        DisCount = disCount;
        MenuId = menuId;
    }

    public String getNameFood() {
        return NameFood;
    }

    public void setNameFood(String nameFood) {
        NameFood = nameFood;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDisCount() {
        return DisCount;
    }

    public void setDisCount(String disCount) {
        DisCount = disCount;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}


