package com.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/greet")
public class GreetingResource {

    private final GraalPyService svc;

    @Inject
    public GreetingResource(GraalPyService svc) {
        this.svc = svc;
    }

    @GET
    @Produces(MediaType.TEXT_XML)
    public String greet() {
        return svc.greet("Quarkus");
    }
}
