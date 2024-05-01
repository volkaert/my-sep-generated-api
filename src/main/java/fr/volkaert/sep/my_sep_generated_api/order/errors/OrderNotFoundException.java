package fr.volkaert.sep.my_sep_generated_api.order.errors;


public class OrderNotFoundException extends RuntimeException {

    private final String orderId;

    public OrderNotFoundException(String orderId) { this.orderId = orderId; }

    @Override
    public String getMessage() {
        return String.format("Order %s not found", orderId);
    }
}
