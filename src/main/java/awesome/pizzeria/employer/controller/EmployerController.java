package awesome.pizzeria.employer.controller;

import awesome.pizzeria.employer.dto.OrderDTO;
import awesome.pizzeria.employer.controller.service.EmployerService;
import awesome.pizzeria.status.OrderStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("api/employer/order")
public class EmployerController {

    private final EmployerService employerService;

    public EmployerController(EmployerService employerService){
        this.employerService = employerService;
    }

    @GetMapping
    public ResponseEntity<OrderDTO> getNextAvailableOrder() {
        OrderDTO order = employerService.getNextAvailableOrder();
        if (order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable String orderId,
                                                              @RequestParam("status") OrderStatus status) {
        try {
            employerService.updateOrderStatus(orderId, status);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
