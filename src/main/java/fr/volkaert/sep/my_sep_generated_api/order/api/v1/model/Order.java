package fr.volkaert.sep.my_sep_generated_api.order.api.v1.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Order object. It is a DTO Data Transfer Object.")
public class Order {

    @NotBlank
    String id; // typically a UUID but we use String for more flexibility

    String someStringData;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss[.SSS]XXX", timezone = "UTC")
    @NotNull
    OffsetDateTime createdAt;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss[.SSS]XXX", timezone = "UTC")
    @NotNull
    OffsetDateTime updatedAt;
}
