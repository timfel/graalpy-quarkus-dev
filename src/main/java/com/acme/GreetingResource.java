package com.acme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greet")
public class GreetingResource {

    private final GraalPyService svc;

    @Autowired
    public GreetingResource(GraalPyService svc) {
        this.svc = svc;
    }

    @GetMapping(produces = MediaType.TEXT_XML_VALUE)
    public String greet() {
        return svc.greet("Spring Boot");
    }
}
