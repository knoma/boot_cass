package com.kweb.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CustomerController {

    @Autowired
    private CustomerRepository repository;

//    @Timed
//    @RequestMapping(value = "/customer", method = RequestMethod.GET)
//    public ResponseEntity<Customer> index(@RequestParam(value="name", required=true) String name) {
//        Customer customer = repository.findByFirstName(name);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Type", "application/json");
////        return customer != null ? customer : new Customer();
//
//        return new ResponseEntity<>(new Customer(), headers, HttpStatus.OK);
//
//    }

    @RequestMapping(value = "/customer/{name}", method = RequestMethod.GET, produces="application/json")
    public ResponseEntity obterPorSigla(@PathVariable String name) {
        Customer customer = repository.findByFirstName(name);
        if(customer != null) return new ResponseEntity<>(customer, HttpStatus.OK);
        else return new ResponseEntity<>( new CustomerDTO(), HttpStatus.OK);
    }

}
