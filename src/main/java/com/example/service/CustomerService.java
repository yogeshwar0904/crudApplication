package com.example.service;

import com.example.model.Customer;
import com.example.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    CustomerDTO getCustomerByID(Long id);
    List<CustomerDTO> getAllCustomer();
    String updateCustomerById(Long id,Customer customer);
    String updateCustomer(Long id, Customer customer);
    String deleteCustomer(Long id);
}
