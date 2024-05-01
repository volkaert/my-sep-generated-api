package fr.volkaert.sep.my_sep_generated_api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class MyHelloRestController {

    @Value("${my-sep-generated-api.hello.message:UNDEFINED}")
    private String helloMessage;

    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    public String sayHello() { return helloMessage; }
}
