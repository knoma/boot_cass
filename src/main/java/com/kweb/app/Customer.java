package com.kweb.app;


import java.util.UUID;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.google.common.base.Objects;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

@Table
@JsonSerialize
public class Customer {

    @PrimaryKey
    private UUID id;

    private String firstName;

    private String lastName;

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Customer(UUID id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Customer() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equal(id, customer.id) &&
                Objects.equal(firstName, customer.firstName) &&
                Objects.equal(lastName, customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, firstName, lastName);
    }
}
