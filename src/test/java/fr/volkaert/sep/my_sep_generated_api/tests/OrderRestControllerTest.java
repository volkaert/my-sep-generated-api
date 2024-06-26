package fr.volkaert.sep.my_sep_generated_api.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.model.CreateOrderRequest;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.model.SearchOrdersRequest;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.model.UpdateOrderRequest;
import fr.volkaert.sep.my_sep_generated_api.order.db.OrderEntity;
import fr.volkaert.sep.my_sep_generated_api.order.db.OrderRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


//  See https://www.baeldung.com/spring-boot-testing
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@ActiveProfiles("test")
@EnableAutoConfiguration
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ComponentScan("fr.volkaert")
@TestPropertySource(locations = "classpath:application-test.yml")
public class OrderRestControllerTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private OrderRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Order(1)
    public void givenValidOrderId_whenGetOrder_thenStatus200() throws Exception {
        repository.deleteAll();
        OrderEntity orderEntity1 = createOrderEntity("1");
        repository.save(orderEntity1);

        mvc.perform(get("/v1/orders/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("someStringData").value("someStringValue1"))
                .andExpect(jsonPath("createdAt").isNotEmpty())
                .andExpect(jsonPath("updatedAt").isNotEmpty());
    }

    @Test
    @Order(2)
    public void givenUnknownOrderId_whenGetOrder_thenStatus404() throws Exception {
        repository.deleteAll();

        mvc.perform(get("/v1/orders/99999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("timestamp").isNotEmpty())
                .andExpect(jsonPath("httpStatusCode").value("404"))
                .andExpect(jsonPath("httpStatusMessage").value("Not Found"))
                .andExpect(jsonPath("errorCode").value("OrderNotFound"))
                .andExpect(jsonPath("errorMessage").value("Order 99999 not found"))
                .andExpect(jsonPath("requestMethod").value("GET"))
                .andExpect(jsonPath("requestURI").value("/v1/orders/99999"));
    }

    @Test
    @Order(3)
    public void givenOrders_whenGetOrders_thenStatus200() throws Exception {
        repository.deleteAll();
        OrderEntity orderEntity1 = createOrderEntity("1");
        OrderEntity orderEntity2 = createOrderEntity("2");
        repository.save(orderEntity1);
        repository.save(orderEntity2);

        mvc.perform(get("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("orders[0].id").value("1"))
                .andExpect(jsonPath("orders[0].someStringData").value("someStringValue1"))
                .andExpect(jsonPath("orders[0].createdAt").isNotEmpty())
                .andExpect(jsonPath("orders[0].updatedAt").isNotEmpty())
                .andExpect(jsonPath("orders[1].id").value("2"))
                .andExpect(jsonPath("orders[1].someStringData").value("someStringValue2"))
                .andExpect(jsonPath("orders[1].createdAt").isNotEmpty())
                .andExpect(jsonPath("orders[1].updatedAt").isNotEmpty());
    }

    @Test
    @Order(4)
    public void givenNoOrders_whenGetOrders_thenStatus200() throws Exception {
        repository.deleteAll();

        mvc.perform(get("/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("orders").isArray())
                .andExpect(jsonPath("orders").isEmpty());
    }

    @Test
    @Order(5)
    public void givenOrders_whenCreateOrder_thenStatus201() throws Exception {
        repository.deleteAll();

        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                        .someStringData("someStringValue")
                        .build();

        mvc.perform(post("/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createOrderRequest)))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("someStringData").value("someStringValue"))
                .andExpect(jsonPath("createdAt").isNotEmpty())
                .andExpect(jsonPath("updatedAt").isNotEmpty());

    }

    @Test
    @Order(6)
    public void givenExistingOrder_whenUpdateOrder_thenStatus200() throws Exception {
        repository.deleteAll();
        OrderEntity orderEntity1 = createOrderEntity("1");
        repository.save(orderEntity1);

        UpdateOrderRequest updateOrderRequest = UpdateOrderRequest.builder()
                .id("1")
                .someStringData("someStringValueUpdated")
                .build();

        mvc.perform(put("/v1/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateOrderRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("id").value("1"))
                .andExpect(jsonPath("someStringData").value("someStringValueUpdated"))
                .andExpect(jsonPath("createdAt").isNotEmpty())
                .andExpect(jsonPath("updatedAt").isNotEmpty());
    }

    @Test
    @Order(7)
    public void givenExistingOrder_whenDeleteOrder_thenStatus204() throws Exception {
        repository.deleteAll();
        OrderEntity orderEntity1 = createOrderEntity("1");
        repository.save(orderEntity1);

        mvc.perform(delete("/v1/orders/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
        mvc.perform(get("/v1/orders/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @Order(8)
    public void givenOrder_whenSearchEventsUsingGET_thenStatus200() throws Exception {
        repository.deleteAll();
        for (int id = 1; id <= 50; id++) {
            OrderEntity orderEntity = createOrderEntity("" + id);
            repository.save(orderEntity);
        }
        mvc.perform(get("/v1/orders/_search?page=1&pageSize=20&sort=updatedAt"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("totalOrderCount").value(50))
                .andExpect(jsonPath("orders[0].id").value("1"))
                .andExpect(jsonPath("orders[19].id").value("20"))
                .andExpect(jsonPath("orders[20]").doesNotExist());
        mvc.perform(get("/v1/orders/_search?page=2&pageSize=20&sort=updatedAt"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("totalOrderCount").value(50))
                .andExpect(jsonPath("orders[0].id").value("21"))
                .andExpect(jsonPath("orders[19].id").value("40"))
                .andExpect(jsonPath("orders[20]").doesNotExist());
        mvc.perform(get("/v1/orders/_search?page=3&pageSize=20&sort=updatedAt"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("totalOrderCount").value(50))
                .andExpect(jsonPath("orders[0].id").value("41"))
                .andExpect(jsonPath("orders[9].id").value("50"))
                .andExpect(jsonPath("orders[10]").doesNotExist());
        mvc.perform(get("/v1/orders/_search?page=4&pageSize=20&sort=updatedAt"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("totalOrderCount").value(50))
                .andExpect(jsonPath("orders").exists())
                .andExpect(jsonPath("orders[0]").doesNotExist());
    }

    @Test
    @Order(9)
    public void givenOrder_whenSearchEventsUsingPOST_thenStatus200() throws Exception {
        repository.deleteAll();
        for (int id = 1; id <= 50; id++) {
            OrderEntity orderEntity = createOrderEntity("" + id);
            repository.save(orderEntity);
        }

        SearchOrdersRequest searchOrdersRequest = SearchOrdersRequest.builder()
                .page(1)
                .pageSize(20)
                .sort("updatedAt")
                .build();
        mvc.perform(post("/v1/orders/_search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchOrdersRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("totalOrderCount").value(50))
                .andExpect(jsonPath("orders[0].id").value("1"))
                .andExpect(jsonPath("orders[19].id").value("20"))
                .andExpect(jsonPath("orders[20]").doesNotExist());
        searchOrdersRequest.setPage(2);
        mvc.perform(post("/v1/orders/_search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchOrdersRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("totalOrderCount").value(50))
                .andExpect(jsonPath("orders[0].id").value("21"))
                .andExpect(jsonPath("orders[19].id").value("40"))
                .andExpect(jsonPath("orders[20]").doesNotExist());
        searchOrdersRequest.setPage(3);
        mvc.perform(post("/v1/orders/_search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchOrdersRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("totalOrderCount").value(50))
                .andExpect(jsonPath("orders[0].id").value("41"))
                .andExpect(jsonPath("orders[9].id").value("50"))
                .andExpect(jsonPath("orders[10]").doesNotExist());
        searchOrdersRequest.setPage(4);
        mvc.perform(post("/v1/orders/_search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchOrdersRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("totalOrderCount").value(50))
                .andExpect(jsonPath("orders").exists())
                .andExpect(jsonPath("orders[0]").doesNotExist());
    }

    private OrderEntity createOrderEntity(@NotBlank String id) {
        OffsetDateTime now = OffsetDateTime.now();
        return OrderEntity.builder()
                .id(id)
                .someStringData("someStringValue" + id)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
}
