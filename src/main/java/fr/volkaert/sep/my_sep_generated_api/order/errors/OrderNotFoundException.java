package fr.volkaert.sep.my_sep_generated_api.order.errors;


import javax.validation.constraints.NotBlank;

public class OrderNotFoundException extends RuntimeException {

    private final String orderId;

    public OrderNotFoundException(@NotBlank String orderId) { this.orderId = orderId; }

    @Override
    public String getMessage() {
        return String.format("Order %s not found", orderId);
    }
}
