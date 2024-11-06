package awesome.pizzeria.pizzeria.model;

import java.time.Instant;
import java.util.List;

public class Order {
    private String uuid;
    private String customerName;
    private List<Pizza> items;
    private OrderStatus orderStatus;
    private Instant orderTime;

    public Order(String uuid, String customerName, List<Pizza> items, OrderStatus orderStatus, Instant orderTime) {
        this.uuid = uuid;
        this.customerName = customerName;
        this.items = items;
        this.orderStatus = orderStatus;
        this.orderTime = orderTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
