package com.example.labactivitieselective1;

public class Frappuccino {
    String name;
    double price, discount;

    public Frappuccino(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public Frappuccino() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }


}
