package com.gmail.dev.surovtsev.yaroslav;

import javax.persistence.*;

@Entity
@Table(name="Menu")
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="dishName", nullable = false)
    private String dishName;

    @Column(name="price")
    private double price;

    @Column(name="weight")
    private double weight;

    @Column(name="isDiscountAvailable")
    private boolean isDiscountAvailable;

    public Menu() {
    }

    public Menu(String dishName, double price, double weight, boolean isDiscountAvailable) {
        this.dishName = dishName;
        this.price = price;
        this.weight = weight;
        this.isDiscountAvailable = isDiscountAvailable;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isDiscountAvailable() {
        return isDiscountAvailable;
    }

    public void setDiscountAvailable(boolean discountAvailable) {
        isDiscountAvailable = discountAvailable;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", dishName='" + dishName + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", isDiscountAvailable=" + isDiscountAvailable +
                '}';
    }
}
