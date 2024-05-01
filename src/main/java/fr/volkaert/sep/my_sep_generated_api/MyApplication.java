package fr.volkaert.sep.my_sep_generated_api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.impl.OrderService;
import fr.volkaert.sep.my_sep_generated_api.order.api.v1.model.CreateOrderRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@SpringBootApplication
public class MyApplication {

    @Value("${my-sep-generated-api.tests.insert-fake-data-in-database-at-startup:false}")
    private boolean insertFakeDataInDatabase;

    public static void main(String[] args) { SpringApplication.run(MyApplication.class, args); }

    @Bean
    public CommandLineRunner run(OrderService orderService) throws Exception {
        return (String[] args) -> {
            System.out.println("My Application started...");

            if (insertFakeDataInDatabase) {
                System.out.println("Inserting fake data in database...");

                for (int i = 1; i <= 100; i++) {
                    orderService.createOrder(CreateOrderRequest.builder()
                            .someStringData("someStringData" + i)
                            .build());
                }
            }
        };
    }

    @Bean
    @Primary
    public ObjectMapper createObjectMapper(Jackson2ObjectMapperBuilder builder) {
        // see https://www.baeldung.com/spring-boot-customize-jackson-objectmapper
        return builder
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .failOnUnknownProperties(false)
                .defaultViewInclusion(false)
                .modules(new JavaTimeModule())
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
    }
}
