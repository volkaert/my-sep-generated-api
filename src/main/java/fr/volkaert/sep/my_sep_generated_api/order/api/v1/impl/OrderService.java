package fr.volkaert.sep.my_sep_generated_api.order.api.v1.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.model.*;
import fr.volkaert.sep.my_sep_generated_api.order.db.OrderEntity;
import fr.volkaert.sep.my_sep_generated_api.order.db.OrderRepository;
import fr.volkaert.sep.my_sep_generated_api.order.errors.OrderNotFoundException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class OrderService {
    final private OrderRepository orderRepository;
    final private ObjectMapper objectMapper;

    public OrderService(OrderRepository orderRepository, ObjectMapper objectMapper) {
        this.orderRepository = orderRepository;
        this.objectMapper = objectMapper;
    }

    @SneakyThrows
    public Order getOrderById(@NotBlank String orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        if (orderEntity == null) throw new OrderNotFoundException(orderId);
        Order order = toOrder(orderEntity);
        log.info("Returning order " + objectMapper.writeValueAsString(order));
        return order;
    }

    @SneakyThrows
    public List<Order> getOrders() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        List<Order> orders = orderEntities.stream().map(this::toOrder).toList();
        log.info("Returning orders " + objectMapper.writeValueAsString(orders));
        return orders;
    }

    @SneakyThrows
    public Order createOrder(@Valid CreateOrderRequest createOrderRequest) {
        OffsetDateTime now = OffsetDateTime.now();
        OrderEntity orderEntityToCreate = OrderEntity.builder()
                .id(UUID.randomUUID().toString())
                .someStringData(createOrderRequest.getSomeStringData())
                .createdAt(now)
                .updatedAt(now)
                .build();
        OrderEntity createdOrderEntity = orderRepository.save(orderEntityToCreate);
        Order createdOrder = toOrder(createdOrderEntity);
        log.info("Returning order " + objectMapper.writeValueAsString(createdOrder));
        return createdOrder;
    }

    @SneakyThrows
    public Order updateOrder(@NotBlank String orderId, @Valid UpdateOrderRequest updateOrderRequest) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        if (orderEntity == null) throw new OrderNotFoundException(orderId);
        OffsetDateTime now = OffsetDateTime.now();
        orderEntity.setSomeStringData(updateOrderRequest.getSomeStringData());
        orderEntity.setUpdatedAt(now);
        OrderEntity updatedOrderEntity = orderRepository.save(orderEntity);
        Order updatedOrder = toOrder(updatedOrderEntity);
        log.info("Returning order " + objectMapper.writeValueAsString(updatedOrder));
        return updatedOrder;
    }

    public void deleteOrder(@NotBlank String orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        if (orderEntity == null) throw new OrderNotFoundException(orderId);
        orderRepository.deleteById(orderId);
    }

    public OrderPage searchOrders(SearchOrdersRequest request) {
        OffsetDateTime now = OffsetDateTime.now();
        OffsetDateTime fromCreatedAt = request.getFromCreatedAt() != null ? request.getFromCreatedAt() : now.minusDays(1);
        OffsetDateTime toCreatedAt = request.getToCreatedAt() != null ? request.getToCreatedAt() : now;
        OffsetDateTime fromUpdatedAt = request.getFromUpdatedAt() != null ? request.getFromUpdatedAt() : now.minusDays(1);
        OffsetDateTime toUpdatedAt = request.getToUpdatedAt() != null ? request.getToUpdatedAt() : now;
        String sortAsString = request.getSort();
        Sort sort = sortAsString.startsWith("-") ? Sort.by(Sort.Direction.DESC, sortAsString.substring(1)) : Sort.by(sortAsString);
        Page<Order> orders = orderRepository.findBy(fromCreatedAt, toCreatedAt, fromUpdatedAt, toUpdatedAt,
                        PageRequest.of(request.getPage()-1, request.getPageSize(), sort))
                .map(this::toOrder);
        return OrderPage.builder()
                .totalOrderCount(orders.getTotalElements())
                .orders(orders.getContent())
                .build();
    }

    private OrderEntity toOrderEntity(@NotNull Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .someStringData(order.getSomeStringData())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    private Order toOrder(@NotNull OrderEntity orderEntity) {
        return Order.builder()
                .id(orderEntity.getId())
                .someStringData(orderEntity.getSomeStringData())
                .createdAt(orderEntity.getCreatedAt())
                .updatedAt(orderEntity.getUpdatedAt())
                .build();
    }
}


