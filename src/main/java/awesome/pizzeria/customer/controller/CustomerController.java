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
    //TODO logging

    CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    /**
     *
     * @param orderRequestDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        //TODO validation (pizza types... and right message error)
        OrderResponseDTO orderResponseDTO = customerService.createOrder(orderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDTO);
    }

    /**
     *
     * @param orderId
     * @return
     */
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> checkOrder(@PathVariable String orderId){
        try {
            return ResponseEntity.ok(customerService.checkOrderStatus(orderId));
        }catch(IllegalArgumentException exc){
            return ResponseEntity.notFound().build();
        }
    }

}
