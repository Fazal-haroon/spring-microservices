package com.example.currencyexchangeservice.controller;

import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);

    @GetMapping("/sample-api2")
    public String sampleApi2() {
        return "Sample API";
    }

    @GetMapping("/sample-api3")
    @Retry(name = "default")
    public String sampleApi3() {
        logger.info(" *** Sample Api call Received (default) *** ");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-fail-dummy-url", String.class);
        return forEntity.getBody();
    }

    @GetMapping("/sample-api")
    @Retry(name = "simple-api", fallbackMethod = "hardcodedResponse")
    public String sampleApi() {
        logger.info(" *** Sample Api call Received (simple-api) *** ");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-fail-dummy-url", String.class);
        return forEntity.getBody();
    }

    public String hardcodedResponse(Exception exception){
        return "fallback-response";
    }
}
