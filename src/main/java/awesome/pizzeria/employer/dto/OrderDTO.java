package awesome.pizzeria.employer.dto;

import awesome.pizzeria.status.OrderStatus;
import awesome.pizzeria.status.Pizza;

import java.util.List;

public class OrderDTO {
    private String orderId;
    private List<Pizza> pizzas;
    private OrderStatus orderStatus;

    public OrderDTO(String orderId, List<Pizza> pizzas, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.pizzas = pizzas;
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

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
