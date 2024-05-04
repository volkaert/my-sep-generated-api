package fr.volkaert.sep.my_sep_generated_api.order.api.v1.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Parameters for the search of Orders. It is a DTO Data Transfer Object.")
public class SearchOrdersRequest {
    OffsetDateTime fromCreatedAt;
    OffsetDateTime toCreatedAt;
    OffsetDateTime fromUpdatedAt;
    OffsetDateTime toUpdatedAt;
    int page = 1;
    int pageSize = 20;
    String sort = "-updatedAt";
}
