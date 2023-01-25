package com.example.demo.restful.webservices.controller;

import com.example.demo.restful.webservices.bean.HelloWorldBean;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

//Rest API
@RestController
public class HelloWorldController {

    private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // /hello-world
    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(path = "/hello-world-bean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("Hello World Bean");
    }

    @GetMapping(path = "/hello-world/path-variable/{name}")
    public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
        return new HelloWorldBean("Hello World Path1, " + name);
    }

    @GetMapping(path = "/hello-world/path-variable2/{name}")
    public HelloWorldBean helloWorldPathVariable2(@PathVariable String name) {
        return new HelloWorldBean(String.format("Hello World Path2, %s", name));
    }

    @GetMapping("/greeting-internationalized")
    public String greetingInternationalized() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);
        /*
         * 'en' - English (Good Morning)
         * 'ar' - Arabic (Sabaah Al Khair)
         * 'ur' - Urdu (Sabaah bakhair)
         * 'pa' - Pashto (Sahar pakhair)
         * */
    }
}
