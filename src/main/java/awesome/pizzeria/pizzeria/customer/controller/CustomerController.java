package awesome.pizzeria.pizzeria.customer.controller;

import awesome.pizzeria.pizzeria.customer.dto.OrderRequestDTO;
import awesome.pizzeria.pizzeria.customer.dto.OrderResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/customer/order")
public class CustomerController {

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Validated @RequestBody OrderRequestDTO orderRequest){
        if(!orderRequest.isValid()){
            return ResponseEntity.badRequest().build();
        }

    }
}
