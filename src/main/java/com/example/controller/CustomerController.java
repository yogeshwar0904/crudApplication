package com.example.controller;

import com.example.model.Customer;
import com.example.model.CustomerDTO;
import com.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public String crateCustomer(@RequestBody Customer customer) {
        String profileCreatedStatus;
        if(customerService.isCustomerExist(customer.getName())){
            profileCreatedStatus = "Oops Customer already Exist";
        } else {
            customerService.saveCustomer(customer);
            profileCreatedStatus = "Customer created";
        }
        return profileCreatedStatus;
    }

    @PostMapping("/login")
    public String login(@RequestBody Customer loginRequest){
       return customerService.login(loginRequest);
    }

    @GetMapping("/{id}")
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerByID(id);
    }

    @GetMapping
    public List<CustomerDTO> getAllCustomers(){
        return customerService.getAllCustomer();
    }

    @PutMapping("/{id}")
    public String updateCustomerById(@PathVariable Long id ,@RequestBody Customer customer){
        return customerService.updateCustomerById(id, customer);
    }

    @PatchMapping("/update/{id}")
    public String updateCustomer(@PathVariable Long id, @RequestBody Customer customer){
       return customerService.updateCustomer(id, customer);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        return customerService.deleteCustomer(id);
    }

}
