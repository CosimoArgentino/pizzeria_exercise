package awesome.pizzeria.employer.controller;

import awesome.pizzeria.employer.service.EmployerService;
import awesome.pizzeria.employer.dto.OrderDTO;
import awesome.pizzeria.status.OrderStatus;
import awesome.pizzeria.status.Pizza;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployerController.class)
public class EmployerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployerService employerService;

    @Test
    void getNextAvailableOrder_shouldReturnOrder_whenOrderAvailable() throws Exception {
        OrderDTO orderDTO = new OrderDTO("123", List.of(Pizza.DIAVOLA), OrderStatus.NEW);

        when(employerService.getNextAvailableOrder()).thenReturn(orderDTO);

        mockMvc.perform(get("/api/employer/order"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.orderId", is("123")))
                .andExpect(jsonPath("$.orderStatus", is("NEW")));
    }

    @Test
    void getNextAvailableOrder_shouldReturnNoContent_whenNoOrderAvailable() throws Exception {
        when(employerService.getNextAvailableOrder()).thenReturn(null);

        mockMvc.perform(get("/api/employer/order"))
                .andExpect(status().isNoContent());
    }

    @Test
    void updateOrderStatus_shouldReturnOk_whenUpdateSuccessful() throws Exception {
        String orderId = "123";
        OrderStatus status = OrderStatus.IN_PROGRESS;

        Mockito.doNothing().when(employerService).updateOrderStatus(eq(orderId), eq(status));

        mockMvc.perform(put("/api/employer/order/{orderId}", orderId)
                        .param("status", status.toString()))
                .andExpect(status().isOk());
    }

    @Test
    void updateOrderStatus_shouldReturnNotFound_whenOrderDoesNotExist() throws Exception {
        String orderId = "123";
        OrderStatus status = OrderStatus.IN_PROGRESS;

        doThrow(new NoSuchElementException()).when(employerService).updateOrderStatus(eq(orderId), eq(status));

        mockMvc.perform(put("/api/employer/order/{orderId}", orderId)
                        .param("status", status.toString()))
                .andExpect(status().isNotFound());
    }
}
