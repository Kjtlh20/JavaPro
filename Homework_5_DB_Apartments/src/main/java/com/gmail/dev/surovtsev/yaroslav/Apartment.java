package com.gmail.dev.surovtsev.yaroslav;

import java.util.Objects;

public class Apartment {
    @Id
    private int id;

    private String district;
    private String address;
    private double area;
    private int numberOfRooms;
    private double price;

    public Apartment() {
    }

    public Apartment(int id, String district, String address, double area, int numberOfRooms, double price) {
        this.id = id;
        this.district = district;
        this.address = address;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apartment apartment = (Apartment) o;
        return id == apartment.id && Double.compare(apartment.area, area) == 0 && numberOfRooms == apartment.numberOfRooms && Double.compare(apartment.price, price) == 0 && Objects.equals(district, apartment.district) && Objects.equals(address, apartment.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, district, address, area, numberOfRooms, price);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", district='" + district + '\'' +
                ", address='" + address + '\'' +
                ", area=" + area +
                ", numberOfRooms=" + numberOfRooms +
                ", price=" + price +
                '}';
    }
}
