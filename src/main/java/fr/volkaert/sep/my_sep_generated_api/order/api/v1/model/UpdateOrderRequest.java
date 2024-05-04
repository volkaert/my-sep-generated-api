package fr.volkaert.sep.my_sep_generated_api.order.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Request to update an Order resource/object. It is a DTO Data Transfer Object.")
public class UpdateOrderRequest {

    @NotBlank
    String id;  // typically a UUID but we use String for more flexibility

    String someStringData;
}
