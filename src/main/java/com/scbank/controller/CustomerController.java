package com.scbank.controller;

import com.scbank.entity.Customer;
import com.scbank.service.CustomerService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 10 Nov, 2024
 */

@Path("/customer") // same thing as @RequestMapping
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @POST
    public Response createCustomer(Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PUT
    @Path("{id}")
    public Response updateCustomer(Customer customer, @PathParam(value = "id") Long id) {
        return customerService.updateCustomer(customer, id);
    }

    @GET
    @Path("{id}")
    public Response getCustomer(@PathParam(value = "id") Long id) {
        return customerService.getCustomerById(id);
    }

    @GET
    @Path("firstName")
    public Response getCustomerByFirstName(@QueryParam(value = "firstName") String firstName) {
        return customerService.getCustomerByFirstName(firstName);
    }

    @GET
    @Path("lastName")
    public Response getCustomerByLastName(@QueryParam(value = "lastName") String lastName) {
        return customerService.getCustomerByLastName(lastName);
    }

}
