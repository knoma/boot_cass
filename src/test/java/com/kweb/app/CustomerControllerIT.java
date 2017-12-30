package com.kweb.app;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.net.MalformedURLException;
import java.net.URL;

import com.datastax.driver.core.utils.UUIDs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Autowired
    private CustomerRepository repository;

    private Customer customer;

    private String host = "http://localhost";

    @Before
    public void setUp() throws Exception {
        customer = new Customer(UUIDs.timeBased(), "test11134", "test");
        repository.save(customer);
    }

    @After
    public void tearDown() {
        repository.delete(customer);
    }

    @Test
    public void getCustomer() throws MalformedURLException {

        URL base = new URL(host + ":" + port + "/customer/" + customer.getFirstName() + "/");

        ResponseEntity<Customer> response = template.getForEntity(base.toString(),
                Customer.class);
        assertThat(response.getBody(), equalTo(customer));
    }


    @Test
    public void getNoCustomer() throws MalformedURLException {

        URL base = new URL(host + ":" + port + "/customer/nouser/");

        ResponseEntity response = template.getForEntity(base.toString(),
                String.class);
        assertThat(response.getBody(), equalTo("{}"));
    }
}