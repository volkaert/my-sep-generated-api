package fr.volkaert.sep.my_sep_generated_api.order.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "List of Orders (because a JSON response should NOT be an array, but an object which contains an array). It is a DTO Data Transfer Object.")
public class OrderList {

    @ArraySchema(arraySchema = @Schema(description = "List of orders", implementation = Order.class, requiredMode = Schema.RequiredMode.REQUIRED))
    List<@Valid Order> orders = new ArrayList<>();
}
