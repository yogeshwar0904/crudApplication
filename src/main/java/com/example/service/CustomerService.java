package com.example.service;

import com.example.model.Customer;
import com.example.model.CustomerDTO;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    CustomerDTO getCustomerByID(Long id);
    List<CustomerDTO> getAllCustomer();
    String updateCustomerById(Long id,Customer customer);
    String updateCustomer(Long id, Customer customer);
    String deleteCustomer(Long id);
    boolean isCustomerExist(String Name);
    String login(Customer loginRequest);
}
