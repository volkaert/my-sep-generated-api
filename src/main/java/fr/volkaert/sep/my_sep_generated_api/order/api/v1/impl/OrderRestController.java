package fr.volkaert.sep.my_sep_generated_api.order.api.v1.impl;

import fr.volkaert.sep.my_sep_generated_api.errors.MyErrorResponseBody;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/orders")
public class OrderRestController {

    final private OrderService orderService;

    public OrderRestController(OrderService orderService) { this.orderService = orderService; }


    //@PreAuthorize("hasAuthority('SCOPE_api.my-sep-generated-api.v1')")
    @Operation(
            method = "GET",
            summary = "Retrieve an Order given its id",
            description = "Retrieve an Order given its id",
            tags = { "Orders" },
            //security = @SecurityRequirement(name = "my-auth-server", scopes = "api.my-sep-generated-api.v1"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order returned with success", content= @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized. You must first authenticate to get a valid token."),
                    @ApiResponse(responseCode = "403", description = "Missing scope or insufficient permissions.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(description = "Generic Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MyErrorResponseBody.class)))
            }
    )
    @GetMapping(path="/{orderId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId, HttpServletRequest httpRequest) {
        Order order = orderService.getOrderById(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }


    //@PreAuthorize("hasAuthority('SCOPE_api.my-sep-generated-api.v1')")
    @Operation(
            method = "GET",
            summary = "Retrieve all Orders",
            description = "Retrieve all Orders. If you want to search Orders with pagination, filtering and sorting, use the _search operation.",
            tags = { "Orders" },
            //security = @SecurityRequirement(name = "my-auth-server", scopes = "api.my-sep-generated-api.v1"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Orders returned with success", content= @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderList.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized. You must first authenticate to get a valid token."),
                    @ApiResponse(responseCode = "403", description = "Missing scope or insufficient permissions.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(description = "Generic Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MyErrorResponseBody.class)))
            }
    )
    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderList> getOrders(HttpServletRequest httpRequest) {
        List<Order> orders = orderService.getOrders();
        OrderList orderList = OrderList.builder().orders(orders).build();
        return ResponseEntity.status(HttpStatus.OK).body(orderList);
    }


    //@PreAuthorize("hasAuthority('SCOPE_api.my-sep-generated-api.v1')")
    @Operation(
            method = "POST",
            summary = "Create an Order",
            description = "Create an Order",
            tags = { "Orders" },
            //security = @SecurityRequirement(name = "my-auth-server", scopes = "api.my-sep-generated-api.v1"),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CreateOrderRequest.class)), required = true, description = "Parameters for the creation of the Order"),
            responses = {
                    @ApiResponse(responseCode = "201", description = "Order created with success", content= @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized. You must first authenticate to get a valid token."),
                    @ApiResponse(responseCode = "403", description = "Missing scope or insufficient permissions.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(description = "Generic Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MyErrorResponseBody.class)))
            }
    )
    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> createOrder(@RequestBody @Valid CreateOrderRequest createOrderRequest, HttpServletRequest httpRequest) {
        Order order = orderService.createOrder(createOrderRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }


    //@PreAuthorize("hasAuthority('SCOPE_api.my-sep-generated-api.v1')")
    @Operation(
            method = "PUT",
            summary = "Update an Order",
            description = "Update an Order",
            tags = { "Orders" },
            //security = @SecurityRequirement(name = "my-auth-server", scopes = "api.my-sep-generated-api.v1"),
            parameters = {
                    @Parameter(name = "orderId", description = "Id of the order to update", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", format = "uuid"))
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UpdateOrderRequest.class)), required = true, description = "Parameters for the update of the Order"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Order updated with success", content= @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = Order.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized. You must first authenticate to get a valid token."),
                    @ApiResponse(responseCode = "403", description = "Missing scope or insufficient permissions.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(description = "Generic Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MyErrorResponseBody.class)))
            }
    )
    @PutMapping(path = "/{orderId}", consumes = MediaType. APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> updateOrder(@PathVariable String orderId, @RequestBody @Valid UpdateOrderRequest updateOrderRequest, HttpServletRequest httpRequest) {
        updateOrderRequest.setId(orderId);  // @TODO: check consistency
        Order order = orderService.updateOrder(updateOrderRequest);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }


    //@PreAuthorize("hasAuthority('SCOPE_api.my-sep-generated-api.v1')")
    @Operation(
            method = "DELETE",
            summary = "Delete an Order",
            description = "Delete an Order",
            tags = { "Orders" },
            //security = @SecurityRequirement(name = "my-auth-server", scopes = "api.my-sep-generated-api.v1"),
            parameters = {
                    @Parameter(name = "orderId", description = "Id of the order to delete", in = ParameterIn.PATH, required = true, schema = @Schema(type = "string", format = "uuid"))
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UpdateOrderRequest.class)), required = true, description = "Parameters for the update of the Order"),
            responses = {
                    @ApiResponse(responseCode = "204", description = "Order delete with success"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized. You must first authenticate to get a valid token."),
                    @ApiResponse(responseCode = "403", description = "Missing scope or insufficient permissions.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(description = "Generic Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MyErrorResponseBody.class)))
            }
    )
    @DeleteMapping(path="/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String orderId, HttpServletRequest httpRequest) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    //@PreAuthorize("hasAuthority('SCOPE_api.my-sep-generated-api.v1')")
    @Operation(
            method = "GET",
            summary = "Search Orders (using GET operation)",
            description = "Search Orders (using GET operation) using pagination, filtering and sorting",
            tags = { "Orders" },
            //security = @SecurityRequirement(name = "my-auth-server", scopes = "api.my-sep-generated-api.v1"),
            parameters = {
                    @Parameter(name = "fromCreatedAt", description = "Orders to retrieve must have a createdAt timestamp greater than fromCreatedAt. Default is now-1day.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", format = "date-time")),
                    @Parameter(name = "toCreatedAt", description = "Orders to retrieve must have a createdAt timestamp lower than toCreatedAt. Default is now.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", format = "date-time")),
                    @Parameter(name = "fromUpdatedAt", description = "Orders to retrieve must have a updatedAt timestamp greater than fromUpdatedAt. Default is now-1day.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", format = "date-time")),
                    @Parameter(name = "toUpdatedAt", description = "Orders to retrieve must have a updatedAt timestamp lower than toUpdatedAt. Default is now.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string", format = "date-time")),
                    @Parameter(name = "page", description = "Page, starting from 1. Default is 1.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "integer")),
                    @Parameter(name = "pageSize", description = "Page size. Default is 20.", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "integer")),
                    @Parameter(name = "sort", description = "Sort. Default is -updatedAt (the '-' prefix means descending sort).", in = ParameterIn.QUERY, required = false, schema = @Schema(type = "string"))
            },
            responses = {
                    @ApiResponse(responseCode = "20O", description = "Orders returned with success", content= @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderPage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized. You must first authenticate to get a valid token."),
                    @ApiResponse(responseCode = "403", description = "Missing scope or insufficient permissions.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(description = "Generic Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MyErrorResponseBody.class)))
            }
    )
    @GetMapping(path="/_search", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderPage> searchOrdersUsingGET(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fromCreatedAt,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime toCreatedAt,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime fromUpdatedAt,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime toUpdatedAt,
            @RequestParam(required = false, defaultValue = "1") int page,
            @RequestParam(required = false, defaultValue = "20") int pageSize,
            @RequestParam(required = false, defaultValue = "-updatedAt") String sort,
            HttpServletRequest httpRequest) {
        SearchOrdersRequest searchOrdersRequest =  SearchOrdersRequest.builder()
                .fromCreatedAt(fromCreatedAt)
                .toCreatedAt(toCreatedAt)
                .fromUpdatedAt(fromUpdatedAt)
                .toUpdatedAt(toUpdatedAt)
                .page(page)
                .pageSize(pageSize)
                .sort(sort)
                .build();
        OrderPage orderPage = orderService.searchOrders(searchOrdersRequest);
        return ResponseEntity.status(HttpStatus.OK).body(orderPage);
    }


    //@PreAuthorize("hasAuthority('SCOPE_api.my-sep-generated-api.v1')")
    @Operation(
            method = "POST",
            summary = "Search Orders (using POST operation)",
            description = "Search Orders (using POST operation) using pagination, filtering and sorting",
            tags = { "Orders" },
            //security = @SecurityRequirement(name = "my-auth-server", scopes = "api.my-sep-generated-api.v1"),
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = SearchOrdersRequest.class)), required = true, description = "Parameters for the search"),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Orders returned with success", content= @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderPage.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized. You must first authenticate to get a valid token."),
                    @ApiResponse(responseCode = "403", description = "Missing scope or insufficient permissions.", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                    @ApiResponse(description = "Generic Error", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = MyErrorResponseBody.class)))
            }
    )
    @PostMapping(path="/_search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderPage> searchOrdersUsingPOST(@RequestBody @Valid SearchOrdersRequest searchOrdersRequest, HttpServletRequest httpRequest) {
        OrderPage orderPage = orderService.searchOrders(searchOrdersRequest);
        return ResponseEntity.status(HttpStatus.OK).body(orderPage);
    }
}
