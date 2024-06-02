package org.mach.source.service;

import com.commercetools.api.client.ByProjectKeyRequestBuilder;
import com.commercetools.api.models.customer.CustomerDraft;
import com.commercetools.api.models.customer.CustomerDraftBuilder;
import com.commercetools.api.models.customer.CustomerSignInResult;
import com.commercetools.api.models.customer_group.CustomerGroupResourceIdentifierBuilder;
import io.vrap.rmf.base.client.ApiHttpResponse;
import org.mach.source.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class CustomerService {

    @Autowired
    private ByProjectKeyRequestBuilder byProjectKeyRequestBuilder;

    public CompletableFuture<CustomerSignInResult> addCustomer(String customerType, CustomerDTO customerDTO){

        CustomerDraft customerDraft = null;
        if(customerType.equalsIgnoreCase("leader")){
            customerDraft = CustomerDraftBuilder.of()
                    .customerGroup(CustomerGroupResourceIdentifierBuilder.of().key("cust-leader").build())
                    .firstName(customerDTO.getFirstName())
                    .lastName(customerDTO.getLastName())
                    .email(customerDTO.getEmail())
                    .password("password@2024")
                    .build();
        } else {
            customerDraft = CustomerDraftBuilder.of()
                    .customerGroup(CustomerGroupResourceIdentifierBuilder.of().key("cust-normal").build())
                    .firstName(customerDTO.getFirstName())
                    .lastName(customerDTO.getLastName())
                    .email(customerDTO.getEmail())
                    .password("password@2024")
                    .build();
        }
        return byProjectKeyRequestBuilder.customers()
                .post(customerDraft)
                .execute().thenApply(ApiHttpResponse::getBody);
    }
}
