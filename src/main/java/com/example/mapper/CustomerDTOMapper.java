package com.example.mapper;

import com.example.model.Customer;
import com.example.model.CustomerDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerDTOMapper  {
    private  ModelMapper modelMapper = new ModelMapper();

    public  CustomerDTO mapToCustomerDTO(Customer customer) {
       return  modelMapper.map(customer, CustomerDTO.class);
    }
}
