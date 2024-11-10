package com.scbank.repository;

import com.scbank.entity.Customer;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 10 Nov, 2024
 */

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    public List<Customer> findByFirstName(String firstName) {

        return list("firstName", firstName);
    }

    public int updateCustomer(Long id, Customer customerToUpdate) {
        return update("UPDATE Customer c set c.firstName=?1, c.lastName=?2, c.email=?3 where c.id=?4",
                customerToUpdate.getFirstName(), customerToUpdate.getLastName(), customerToUpdate.getEmail(), id);
    }
}
