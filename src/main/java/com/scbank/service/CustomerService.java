package com.scbank.service;

import com.scbank.entity.Customer;
import com.scbank.repository.CustomerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 10 Nov, 2024
 */

@ApplicationScoped
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Transactional
    public Response createCustomer(Customer customer) {
        customerRepository.persist(customer);

        if(customerRepository.isPersistent(customer)) {
            return Response.ok(customer).build();
        }
//        return Response.serverError().build();
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    @Transactional
    public Response updateCustomer(Customer customer, Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findByIdOptional(id);
        if(optionalCustomer.isPresent()) {
            Customer customerToUpdate = optionalCustomer.get();
            updateCustomer(customerToUpdate, customer);

            int updatedRecord = customerRepository.updateCustomer(id, customerToUpdate);
            if(updatedRecord > 0) {
                return Response.ok(customerToUpdate).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    public Response getCustomerById(Long id) {
        return customerRepository.find("id", id)
                .singleResultOptional()
                .map(customer -> Response.ok(customer).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    public Response getCustomerByFirstName(String firstName) {
        return Response.ok(customerRepository.findByFirstName(firstName)).build();
    }

    public Response getCustomerByLastName(String lastName) {
        return customerRepository.find("lastName", lastName)
                .singleResultOptional()
                .map(customer -> Response.ok(customer).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }




    private Customer updateCustomer(Customer update, Customer existing) {

        existing.setFirstName(update.getFirstName());
        existing.setLastName(update.getLastName());
        existing.setEmail(update.getEmail());
        return existing;
    }
}
