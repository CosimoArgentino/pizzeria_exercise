package awesome.pizzeria.employer.dto;

import awesome.pizzeria.status.OrderStatus;
import awesome.pizzeria.status.Pizza;

import java.util.List;

public class OrderDTO {
    private String orderId;
    private List<Pizza> items;
    private OrderStatus orderStatus;

    public OrderDTO(String orderId, List<Pizza> items, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.items = items;
        this.orderStatus = orderStatus;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public List<Pizza> getItems() {
        return items;
    }

    public void setItems(List<Pizza> items) {
        this.items = items;
    }
}
