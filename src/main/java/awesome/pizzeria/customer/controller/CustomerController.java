package awesome.pizzeria.customer.controller;

import awesome.pizzeria.customer.dto.OrderRequestDTO;
import awesome.pizzeria.customer.dto.OrderResponseDTO;
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
        try {
            return ResponseEntity.ok(customerService.checkOrderStatus(orderId));
        }catch(IllegalArgumentException exc){
            return ResponseEntity.notFound().build();
        }
    }

}
