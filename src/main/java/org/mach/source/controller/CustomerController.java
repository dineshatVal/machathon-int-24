package org.mach.source.controller;

import com.commercetools.api.models.customer.CustomerSignInResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.mach.source.dto.CustomerDTO;
import org.mach.source.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomer")
    public CompletableFuture<CustomerSignInResult> addCustomer(@RequestParam String customerType, @RequestBody CustomerDTO customerDTO) throws JsonProcessingException {
        return customerService.addCustomer(customerType, customerDTO);
    }
}
