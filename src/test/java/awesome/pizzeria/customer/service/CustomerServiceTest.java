package awesome.pizzeria.customer.service;

import awesome.pizzeria.customer.dto.OrderRequestDTO;
import awesome.pizzeria.customer.dto.OrderResponseDTO;
import awesome.pizzeria.model.Order;
import awesome.pizzeria.status.OrderStatus;
import awesome.pizzeria.status.Pizza;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    private static final String ORDER_QUEUE_KEY = "orderQueue";

    @Mock
    private RedisTemplate<String, Order> orderRedisTemplate;

    @Mock
    private RedisTemplate<String, String> queueRedisTemplate;

    @Mock
    private ValueOperations<String, Order> valueOperations;

    @Mock
    private ListOperations<String, String> listOperations;

    private CustomerService customerService; // Remove @InjectMocks

    private OrderRequestDTO orderRequestDTO;

    @BeforeEach
    void setUp() {
        // Explicitly instantiate CustomerService with mocked dependencies
        customerService = new CustomerService(orderRedisTemplate, queueRedisTemplate);

        // Initialize sample OrderRequestDTO
        orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setCustomerName("John Doe");
        orderRequestDTO.setPizzas(List.of(Pizza.MARGHERITA, Pizza.DIAVOLA));
    }

    @Test
    void createOrder_shouldStoreOrderInRedisAndQueue() {
        when(orderRedisTemplate.opsForValue()).thenReturn(valueOperations);
        when(queueRedisTemplate.opsForList()).thenReturn(listOperations);
        doNothing().when(valueOperations).set(anyString(), any(Order.class));
        when(listOperations.leftPush(eq(ORDER_QUEUE_KEY), anyString())).thenReturn(1L);

        OrderResponseDTO response = customerService.createOrder(orderRequestDTO);

        assertNotNull(response);
        assertEquals(OrderStatus.NEW, response.getOrderStatus());

        verify(valueOperations).set(anyString(), any(Order.class));
        verify(listOperations).leftPush(eq(ORDER_QUEUE_KEY), anyString());
    }

    @Test
    void checkOrderStatus_shouldReturnOrderStatus_whenOrderExists() {
        String orderId = "test-order-id";
        Order order = new Order(orderId, "John Doe", List.of(Pizza.MARGHERITA, Pizza.DIAVOLA), OrderStatus.NEW, Instant.now());
        when(orderRedisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get(orderId)).thenReturn(order);

        OrderResponseDTO response = customerService.checkOrderStatus(orderId);

        assertNotNull(response);
        assertEquals(orderId, response.getOrderId());
        assertEquals(OrderStatus.NEW, response.getOrderStatus());
    }

    @Test
    void checkOrderStatus_shouldThrowException_whenOrderDoesNotExist() {
        when(orderRedisTemplate.opsForValue()).thenReturn(valueOperations);
        when(valueOperations.get(anyString())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> customerService.checkOrderStatus("invalidId"));
    }
}
