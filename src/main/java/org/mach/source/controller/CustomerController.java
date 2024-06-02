package org.mach.source.controller;

import com.commercetools.api.models.customer.Customer;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.mach.source.dto.CustomerDTO;
import org.mach.source.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping("/signUpCustomer")
    public CompletableFuture<CustomerSignInResult> addCustomer(@RequestParam String customerType, @RequestBody CustomerDTO customerDTO) throws ExecutionException, InterruptedException {
        return customerService.addCustomer(customerType, customerDTO);
    }

    @PostMapping("/addToCommunity")
    public CompletableFuture<String> addToCommunity(@RequestParam String community, @RequestParam String customerid) throws ExecutionException, InterruptedException {
        return customerService.addToCommunity(community, customerid);
    }

    @PostMapping("/signInCustomer")
    public CompletableFuture<CustomerSignInResult> signInCustomer(@RequestBody CustomerDTO customerDTO) throws ExecutionException, InterruptedException {
        return customerService.getCustomer(customerDTO);
    }
}
