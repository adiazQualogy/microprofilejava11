package com.example.intento1.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.faulttolerance.Fallback;

@ApplicationScoped
@Path("/hello")
public class HelloWorldEndpoint {

	@GET
	@Produces("text/plain")
	@Fallback(fallbackMethod = "getFallback")
	public Response doGet(@QueryParam("fail") boolean fail) {
		var message = "Hello from Thorntail! ";
		if (fail) {
			throw new RuntimeException(message);
		} else {
			var proofOfJava11 = "\nIs this running on a version below Java 11? " + message.isBlank();
			return Response.ok(message + proofOfJava11).build();
		}
	}

	public Response getFallback(@QueryParam("fail") boolean fail) {
		return Response.ok("Fallback message executed because fail was forced: " + fail).build();
	}
}
