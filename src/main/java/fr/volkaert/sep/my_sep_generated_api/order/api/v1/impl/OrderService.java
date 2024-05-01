package fr.volkaert.sep.my_sep_generated_api.order.api.v1.impl;

import fr.volkaert.sep.my_sep_generated_api.order.api.v1.CreateOrderRequest;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.Order;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.UpdateOrderRequest;
import fr.volkaert.sep.my_sep_generated_api.order.db.OrderEntity;
import fr.volkaert.sep.my_sep_generated_api.order.db.OrderRepository;
import fr.volkaert.sep.my_sep_generated_api.order.errors.OrderNotFoundException;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class OrderService {
    final private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrderById(String orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        if (orderEntity == null) throw new OrderNotFoundException(orderId);
        return toOrder(orderEntity);
    }

    public Order createOrder (CreateOrderRequest createOrderRequest) {
        OffsetDateTime now = OffsetDateTime.now();
        OrderEntity orderEntityToCreate = OrderEntity.builder()
                .id(UUID.randomUUID().toString())
                .someUsefulStringData(createOrderRequest.getSomeUsefulStringData())
                .createdAt(now)
                .updatedAt(now)
                .build();
        OrderEntity createdOrderEntity = orderRepository.save(orderEntityToCreate);
        Order createdOrder = toOrder(createdOrderEntity);
        return createdOrder;
    }

    public Order updateOrder (String orderId, UpdateOrderRequest updateOrderRequest) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        if (orderEntity == null) throw new OrderNotFoundException(orderId);
        OffsetDateTime now = OffsetDateTime.now();
        orderEntity.setUpdatedAt(now);
        OrderEntity updatedOrderEntity = orderRepository.save(orderEntity);
        Order updatedOrder = toOrder(updatedOrderEntity);
        return updatedOrder;
    }

    public void deleteOrder(String orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElse(null);
        if (orderEntity == null) throw new OrderNotFoundException(orderId);
        orderRepository.deleteById(orderId);
    }

    private OrderEntity toOrderEntity(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .someUsefulStringData(order.getSomeUsefulStringData())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

    private Order toOrder(OrderEntity orderEntity) {
        return Order.builder()
                .id(orderEntity.getId())
                .someUsefulStringData(orderEntity.getSomeUsefulStringData())
                .createdAt(orderEntity.getCreatedAt())
                .updatedAt(orderEntity.getUpdatedAt())
                .build();
    }
}


