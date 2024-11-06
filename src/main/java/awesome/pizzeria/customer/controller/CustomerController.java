package awesome.pizzeria.customer.controller;

import awesome.pizzeria.dto.OrderRequestDTO;
import awesome.pizzeria.dto.OrderResponseDTO;
import awesome.pizzeria.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/customer/order")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        OrderResponseDTO orderResponseDTO = customerService.createOrder(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDTO);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> checkOrder(@PathVariable String orderId){
        return ResponseEntity.ok(customerService.checkOrderStatus(orderId));
    }

}
