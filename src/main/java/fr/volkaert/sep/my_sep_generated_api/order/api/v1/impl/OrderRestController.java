package fr.volkaert.sep.my_sep_generated_api.order.api.v1.impl;

import fr.volkaert.sep.my_sep_generated_api.order.api.v1.model.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderRestController {

    final private OrderService orderService;

    public OrderRestController(OrderService orderService) { this.orderService = orderService; }


    @GetMapping(path="/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId, HttpServletRequest httpRequest) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderList> getOrders(HttpServletRequest httpRequest) {
        List<Order> orders = orderService.getOrders();
        OrderList orderList = OrderList.builder().orders(orders).build();
        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }

    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest, HttpServletRequest httpRequest) {
        Order order = orderService.createOrder(createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PutMapping(path = "/{orderId}", consumes = MediaType. APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> updateOrder(@PathVariable String orderId, @RequestBody @Valid UpdateOrderRequest updateOrderRequest, HttpServletRequest httpRequest) {
        Order order = orderService.updateOrder(orderId, updateOrderRequest);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @DeleteMapping(path="/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderId, HttpServletRequest httpRequest) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping(path="/_search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderPage> searchOrdersUsingGET(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fromCreatedAt,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime toCreatedAt,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fromUpdatedAt,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime toUpdatedAt,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "20") int pageSize,
            @RequestParam(required = false, defaultValue = "-updatedAt") String sort,
            HttpServletRequest httpRequest) {
        SearchOrdersRequest searchOrdersRequest =  SearchOrdersRequest.builder()
                .fromCreatedAt(fromCreatedAt)
                .toCreatedAt(toCreatedAt)
                .fromUpdatedAt(fromUpdatedAt)
                .toUpdatedAt(toUpdatedAt)
                .page(page)
                .pageSize(pageSize)
                .sort(sort)
                .build();
        OrderPage orderPage = orderService.searchOrders(searchOrdersRequest);
        return ResponseEntity.status(HttpStatus.OK).body(orderPage);
    }

    @PostMapping(path="/_search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderPage> searchOrdersUsingPOST(@RequestBody @Valid SearchOrdersRequest searchOrdersRequest, HttpServletRequest httpRequest) {
        OrderPage orderPage = orderService.searchOrders(searchOrdersRequest);
        return ResponseEntity.status(HttpStatus.OK).body(orderPage);
    }
}
