package com.gmail.dev.surovtsev.yaroslav;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class Main {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection()) {

            AbstractDAO<Product> productDAO = new ProductDAOImpl(conn, Product.class.getSimpleName());
            productDAO.createTable(Product.class);

            AbstractDAO<Customer> customerDAO = new CustomerDAOImpl(conn, Customer.class.getSimpleName());
            customerDAO.createTable(Customer.class);

            AbstractDAO<Orders> orderDAO = new OrderDAOImpl(conn, Orders.class.getSimpleName());
            orderDAO.createTable(Orders.class);

            AbstractDAO<OrderDetail> orderDetailDAO = new OrderDetailDAOImpl(conn, OrderDetail.class.getSimpleName());
            orderDetailDAO.createTable(OrderDetail.class);

            for (int i = 0; i < 10; i++) {
                Customer cust = new Customer();
                cust.setName("Name" + i);
                customerDAO.save(cust);

                Orders ord = new Orders();
                Optional<Customer> res = customerDAO.get(i + 1, Customer.class);
                ord.setCustomerId(res.get().getId());
                ord.setDate(new Date());
                ord.setNumber(i + 1);
                orderDAO.save(ord);

                for (int j = 0; j < 10; j++) {
                    Product prod = new Product();
                    prod.setName("Name" + i);
                    prod.setPrice(50.11 + i);
                    productDAO.save(prod);

                    OrderDetail ordDet = new OrderDetail();
                    ordDet.setPrice(prod.getPrice() + j);
                    ordDet.setQuantity(1.456 + j);
                    Optional<Product> res2 = productDAO.get(j + 1, Product.class);
                    ordDet.setProductID(res2.get().getId());
                    Optional<Orders> res3 = orderDAO.get(i + 1, Orders.class);
                    ordDet.setOrderID(res3.get().getId());
                    orderDetailDAO.save(ordDet);
                }
            }

            List<Customer> customers = customerDAO.getAll(Customer.class);
            customers.forEach(System.out::println);
            System.out.println();

            List<Product> products = productDAO.getAll(Product.class);
            products.forEach(System.out::println);
            System.out.println();

            List<Orders> orders = orderDAO.getAll(Orders.class);
            orders.forEach(System.out::println);
            System.out.println();

            List<OrderDetail> orderDetails = orderDetailDAO.getAll(OrderDetail.class);
            orderDetails.forEach(System.out::println);
        }
    }
}
