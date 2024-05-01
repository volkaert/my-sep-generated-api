package fr.volkaert.sep.my_sep_generated_api.order.api.v1.impl;

import fr.volkaert.sep.my_sep_generated_api.order.api.v1.model.CreateOrderRequest;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.model.Order;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.model.UpdateOrderRequest;
import fr.volkaert.sep.my_sep_generated_api.order.db.OrderEntity;
import fr.volkaert.sep.my_sep_generated_api.order.db.OrderRepository;
import fr.volkaert.sep.my_sep_generated_api.order.errors.OrderNotFoundException;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    final private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(@NotBlank String orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        if (orderEntity == null) throw new OrderNotFoundException(orderId);
        return toOrder(orderEntity);
    }

    public List<Order> getOrders() {
        List<OrderEntity> orderEntities = orderRepository.findAll();
        List<Order> orders = orderEntities.stream().map(this::toOrder).toList();
        return orders;
    }

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
        return createdOrder;
    }

    public Order updateOrder(@NotBlank String orderId, @Valid UpdateOrderRequest updateOrderRequest) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        if (orderEntity == null) throw new OrderNotFoundException(orderId);
        OffsetDateTime now = OffsetDateTime.now();
        orderEntity.setSomeStringData(updateOrderRequest.getSomeStringData());
        orderEntity.setUpdatedAt(now);
        OrderEntity updatedOrderEntity = orderRepository.save(orderEntity);
        Order updatedOrder = toOrder(updatedOrderEntity);
        return updatedOrder;
    }

    public void deleteOrder(@NotBlank String orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        if (orderEntity == null) throw new OrderNotFoundException(orderId);
        orderRepository.deleteById(orderId);
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


