package com.example.intento1.rest;

import org.eclipse.microprofile.metrics.annotation.Counted;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/hello")
public class HelloWorldEndpoint {

	@GET
	@Produces("text/plain")
	@Counted(name = "counter",
			absolute = true,
			monotonic = true,
			displayName = "Simple counter",
			description = "Metrics to show how many times the end point was called.")
	public Response doGet() {
		String message = "Hello from Thorntail! ";

		return Response.ok(message + message.isBlank()).build();
	}
}
