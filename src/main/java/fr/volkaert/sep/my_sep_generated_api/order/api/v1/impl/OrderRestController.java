package fr.volkaert.sep.my_sep_generated_api.order.api.v1.impl;

import fr.volkaert.sep.my_sep_generated_api.order.api.v1.CreateOrderRequest;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.Order;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.UpdateOrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/orders")
public class OrderRestController {

    final private OrderService orderService;

    public OrderRestController(OrderService orderService) { this.orderService = orderService; }


    @GetMapping(path="/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getOrderById(@PathVariable String orderId, HttpServletRequest httpRequest) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PostMapping(path="/orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest, HttpServletRequest httpRequest) {
        Order order = orderService.createOrder(createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping(path = "/orders/{orderId}", consumes = MediaType. APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateOrder(@PathVariable String orderId, @RequestBody @Valid UpdateOrderRequest updateOrderRequest, HttpServletRequest httpRequest) {
        Order order = orderService.updateOrder(orderId, updateOrderRequest);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @DeleteMapping(path="/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable String orderId, HttpServletRequest httpRequest) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
