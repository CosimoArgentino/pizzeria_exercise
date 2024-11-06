package awesome.pizzeria.customer.service;

import awesome.pizzeria.dto.OrderRequestDTO;
import awesome.pizzeria.dto.OrderResponseDTO;
import awesome.pizzeria.model.Order;
import awesome.pizzeria.status.OrderStatus;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class CustomerService {

    private final RedisTemplate<String, Order> orderRedisTemplate;
    private final RedisTemplate<String, String> queueRedisTemplate;
    private static final String ORDER_QUEUE_KEY = "orderQueue";

    public CustomerService(RedisTemplate<String, Order> orderRedisTemplate, RedisTemplate<String, String> queueRedisTemplate) {
        this.orderRedisTemplate = orderRedisTemplate;
        this.queueRedisTemplate = queueRedisTemplate;
    }

    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO){
        String orderID = UUID.randomUUID().toString();
        Order order = new Order(orderID, orderRequestDTO.getCustomerName(),
                orderRequestDTO.getPizzas(), OrderStatus.NEW, Instant.now());

        orderRedisTemplate.opsForValue().set(orderID, order);
        queueRedisTemplate.opsForList().leftPush(ORDER_QUEUE_KEY, orderID);

        return new OrderResponseDTO(orderID, OrderStatus.NEW);
    }

    public OrderResponseDTO checkOrderStatus(String orderId) {
        Order order = orderRedisTemplate.opsForValue().get(orderId);
        if (order == null) {
            throw new IllegalArgumentException("Order with ID " + orderId + " not found");
        }

        return new OrderResponseDTO(orderId, order.getOrderStatus());
    }
}
