package awesome.pizzeria.model;

import awesome.pizzeria.status.OrderStatus;
import awesome.pizzeria.status.Pizza;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class Order implements Serializable {
    private String orderId;
    private String customerName;
    private List<Pizza> items;
    private OrderStatus orderStatus;
    private Instant orderTime;

    public Order(String orderId, String customerName, List<Pizza> items, OrderStatus orderStatus, Instant orderTime) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.items = items;
        this.orderStatus = orderStatus;
        this.orderTime = orderTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Pizza> getItems() {
        return items;
    }

    public void setItems(List<Pizza> items) {
        this.items = items;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Instant getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Instant orderTime) {
        this.orderTime = orderTime;
    }
}
