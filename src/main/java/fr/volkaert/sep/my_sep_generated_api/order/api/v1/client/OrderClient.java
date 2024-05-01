package fr.volkaert.sep.my_sep_generated_api.order.api.v1.client;

import fr.volkaert.sep.my_sep_generated_api.order.api.v1.model.CreateOrderRequest;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.model.Order;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.model.OrderList;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.model.UpdateOrderRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public interface OrderClient {
    Order getOrderById(@NotBlank String orderId);
    OrderList getOrders();
    Order createOrder(@Valid CreateOrderRequest createOrderRequest);
    Order updateOrder(@NotBlank String orderId, @Valid UpdateOrderRequest updateOrderRequest);
    void deleteOrder(@NotBlank String orderId);
}
