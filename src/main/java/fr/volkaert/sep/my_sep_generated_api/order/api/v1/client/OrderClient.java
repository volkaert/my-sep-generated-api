package fr.volkaert.sep.my_sep_generated_api.order.api.v1.client;

import fr.volkaert.sep.my_sep_generated_api.order.api.v1.model.*;
import org.springframework.data.domain.Sort;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

public interface OrderClient {
    Order getOrderById(@NotBlank String orderId);
    OrderList getOrders();
    Order createOrder(@Valid CreateOrderRequest createOrderRequest);
    Order updateOrder(@NotBlank String orderId, @Valid UpdateOrderRequest updateOrderRequest);
    void deleteOrder(@NotBlank String orderId);
    OrderPage searchOrders(@Nullable OffsetDateTime fromCreatedAt, @Nullable OffsetDateTime toCreatedAt,
                           @Nullable OffsetDateTime fromUpdatedAt, @Nullable OffsetDateTime toUpdatedAt,
                           int page, @Nullable Integer pageSize, @Nullable Sort sort);

}
