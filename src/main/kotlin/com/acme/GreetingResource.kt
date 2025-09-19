package com.acme

import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/greet")
class GreetingResource @Inject constructor(
    private val svc: GraalPyService
) {
    @GET
    @Produces(MediaType.TEXT_XML)
    fun greet() = svc.greet("Quarkus")
}
