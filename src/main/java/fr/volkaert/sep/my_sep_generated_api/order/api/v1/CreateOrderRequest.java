package fr.volkaert.sep.my_sep_generated_api.order.api.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Request to create an Order resource/object. It is a DTO Data Transfer Object.")
public class CreateOrderRequest {
    String someUsefulStringData;
}
