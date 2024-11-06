package awesome.pizzeria.dto;

import awesome.pizzeria.status.OrderStatus;

public class OrderResponseDTO {
    private String orderId;
    private OrderStatus orderStatus;

    public OrderResponseDTO(String orderId, OrderStatus orderStatus) {
        this.orderId = orderId;
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
}

