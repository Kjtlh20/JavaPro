package com.gmail.dev.surovtsev.yaroslav;

public class OrderDetail {
    @Id
    private int id;

    private int orderID;
    private int productID;
    private double price;
    private double quantity;

    public OrderDetail() {
    }

    public OrderDetail(int orderID, int productID, double price, double quantity) {
        this.orderID = orderID;
        this.productID = productID;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", orderID=" + orderID +
                ", productID=" + productID +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
