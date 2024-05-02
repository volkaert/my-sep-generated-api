package fr.volkaert.sep.my_sep_generated_api.order.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Page of orders returned by the API when pagination is used for large number of orders. It is a DTO Data Transfer Object.")
public class OrderPage {

    @NotNull
    @Schema(type = "integer", description = "Total order count",requiredMode = Schema.RequiredMode.REQUIRED)
    private Long totalOrderCount;

    @ArraySchema(arraySchema = @Schema(description = "List of orders for this page", implementation = Order.class, requiredMode = Schema.RequiredMode.REQUIRED))
    List<Order> orders = new ArrayList<>();
}
