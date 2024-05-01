package fr.volkaert.sep.my_sep_generated_api.errors;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Generic HTP Error Response")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyErrorResponseBody {
    @Schema(description = "Timestamp of the error", example="2025-01-29118:05:38,.4292")
    private Instant timestamp;

    @Schema(description = "HTTP status code (404, 400, 500...", example="400")
    private int httpStatusCode;

    @Schema(description = "HTTP status massage (\"Not Found\", \"Bad Request\", \"Internal Server Error\"...)", example = "Bad Request")
    private String httpStatusMessage;

    @Schema(description = "The applicative error code. It is a machine readable code. Used when BAD REQUEST error to provide details about the error.", example = "INVALID EMAIL")
    private String errorCode;

    @Schema(description = "The applicative error message. It is a human readable English message.", example = "The email address is not valid")
    private String errorMessage;

    @Schema(description = "HTTP method used to make the request (\"GET\", \"POST\", \"PUT\", \"DELETE\"...)", example = "POST")
    private String requestMethod;

    @Schema(description = "HTTP URI used to make the request", example = "/v1/user-account/users")
    private String requestURI;

    public static MyErrorResponseBody build(HttpStatus httpStatus, String errorCode, String errorMessage, HttpServletRequest httpRequest) {
        return MyErrorResponseBody.builder()
                .timestamp(Instant.now())
                .httpStatusCode(httpStatus.value())
                .httpStatusMessage(httpStatus.getReasonPhrase())
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .requestMethod(httpRequest.getMethod())
                .requestURI(httpRequest.getRequestURI())
                .build();
    }
}
