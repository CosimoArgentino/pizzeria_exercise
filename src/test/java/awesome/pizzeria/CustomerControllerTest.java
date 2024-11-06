package awesome.pizzeria;

import awesome.pizzeria.customer.controller.CustomerController;
import awesome.pizzeria.customer.dto.OrderRequestDTO;
import awesome.pizzeria.customer.dto.OrderResponseDTO;
import awesome.pizzeria.customer.service.CustomerService;
import awesome.pizzeria.status.OrderStatus;
import awesome.pizzeria.status.Pizza;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    private OrderRequestDTO orderRequestDTO;
    private OrderResponseDTO orderResponseDTO;

    @BeforeEach
    void setUp() {
        orderRequestDTO = new OrderRequestDTO();
        orderRequestDTO.setCustomerName("John Doe");
        orderRequestDTO.setPizzas(List.of(Pizza.MARGHERITA, Pizza.DIAVOLA));

        orderResponseDTO = new OrderResponseDTO("12345", OrderStatus.IN_PROGRESS);
    }

    @Test
    void createOrder_shouldReturnCreatedOrder() throws Exception {
        when(customerService.createOrder(any(OrderRequestDTO.class))).thenReturn(orderResponseDTO);

        mockMvc.perform(post("/api/customer/order")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"customerName\": \"John Doe\", \"pizzas\": [\"MARGHERITA\", \"DIAVOLA\"] }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderId", is("12345")))
                .andExpect(jsonPath("$.orderStatus", is("IN_PROGRESS")));
    }

    @Test
    void checkOrder_shouldReturnOrderStatus() throws Exception {
        when(customerService.checkOrderStatus(eq("12345"))).thenReturn(orderResponseDTO);

        mockMvc.perform(get("/api/customer/order/12345"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId", is("12345")))
                .andExpect(jsonPath("$.orderStatus", is("IN_PROGRESS")));
    }

    @Test
    void checkOrder_shouldReturnNotFoundForInvalidOrderId() throws Exception {
        when(customerService.checkOrderStatus(eq("invalidId"))).thenThrow(new IllegalArgumentException());

        mockMvc.perform(get("/api/customer/order/invalidId"))
                .andExpect(status().isNotFound());
    }
}
