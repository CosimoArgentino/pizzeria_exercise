package awesome.pizzeria.employer.service;

import awesome.pizzeria.employer.dto.OrderDTO;
import awesome.pizzeria.model.Order;
import awesome.pizzeria.status.OrderStatus;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class EmployerService {

    private final RedisTemplate<String, Order> orderRedisTemplate;
    private final RedisTemplate<String, String> queueRedisTemplate;
    private static final String ORDER_QUEUE_KEY = "orderQueue";

    public EmployerService(RedisTemplate<String, Order> orderRedisTemplate, RedisTemplate<String, String> queueRedisTemplate) {
        this.orderRedisTemplate = orderRedisTemplate;
        this.queueRedisTemplate = queueRedisTemplate;
    }

    public OrderDTO getNextAvailableOrder(){
        while (true) {
            String nextOrderId = queueRedisTemplate.opsForList().rightPop(ORDER_QUEUE_KEY);
            if (nextOrderId == null) {
                return null;
            }

            Order order = orderRedisTemplate.opsForValue().get(nextOrderId);
            if (order == null) {
                continue;
            }

            if (order.getOrderStatus() == OrderStatus.NEW) {
                order.setOrderStatus(OrderStatus.IN_PROGRESS);
                orderRedisTemplate.opsForValue().set(nextOrderId, order);
                return new OrderDTO(order.getOrderId(), order.getItems(), order.getOrderStatus());
            }
        }
    }

    public void updateOrderStatus(String orderId, OrderStatus orderStatus){
        Order order = orderRedisTemplate.opsForValue().get(orderId);
        if (order != null) {
            order.setOrderStatus(orderStatus);
            orderRedisTemplate.opsForValue().set(orderId, order);
            return;
        }
        throw new NoSuchElementException();
    }
}
