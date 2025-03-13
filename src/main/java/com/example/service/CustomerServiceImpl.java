package com.example.service;

import com.example.mapper.CustomerDTOMapper;
import com.example.model.Customer;
import com.example.model.CustomerDTO;
import com.example.repository.CustomerRepository;
import com.example.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final CustomerDTOMapper customerDTOMapper;

    @Autowired
    private final AuthenticationManager authenticationManager;

    @Autowired
    private final JWTUtil jwtUtil;


    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerDTOMapper customerDTOMapper, AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.customerRepository = customerRepository;
        this.customerDTOMapper = customerDTOMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public CustomerDTO getCustomerByID(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        return customerDTOMapper.mapToCustomerDTO(customer);
    }


    @Override
        public List<CustomerDTO> getAllCustomer(){
           List <Customer> getAllCustomer = customerRepository.findAll();
            return getAllCustomer
                    .stream().map(customerDTOMapper :: mapToCustomerDTO )
                    .collect(Collectors.toList());
        }

    @Override
    public String updateCustomerById(Long id,Customer customer) {
        List<Customer> allCustomers = customerRepository.findAll();
        String result ="Object doesn't exist";
        for (int i = 0; i < allCustomers.size(); i++) {
            if (allCustomers.get(i).getID().equals(id)) {
                allCustomers.get(i).setName(customer.getName());
                customerRepository.save(customer);
                  result = "update Successfully";
                  break;
            }
        }
        return result;
    }

    @Override
    public String updateCustomer(Long id, Customer customer) {
        List<Customer> updateCustomer = customerRepository.findAll();
        String updateMessage = "Not Updated";
        System.out.println("is the Object Present " + customer);
        for (int i = 0; i < updateCustomer.size(); i++){
            if(updateCustomer.get(i).getID().equals(id)) {
                System.out.println("checking Object is "+ customer.name);
                updateCustomer.get(i).setName(customer.name);
                customerRepository.save(customer);
                updateMessage = "Yeah updated";
                break;
            }
        }
        return updateMessage;
    }

    @Override
    public String deleteCustomer(Long id){
        List<Customer> customerIsPresent = customerRepository.findAll();
        String deleteMessage ="";
        for(int i = 0; i<customerIsPresent.size();i++){
            if(customerIsPresent.get(i).id.equals(id)){
                System.out.println("yeah matched");
                customerRepository.deleteById(id);
                if(customerIsPresent.get(i).equals(id)){
                    deleteMessage = "Hoo no still not deleted";
                } else {
                    deleteMessage = "Awesome deleted successfully";
                    break;
                }
            } else {
                deleteMessage ="OOPs Id doesn't exist";
            }
        }
        return deleteMessage;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByName(username);
        return new org.springframework.security.core.
                userdetails.User(customer.getName(), customer.getPassword(), customer.getRoles().stream().map(role ->
                new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
    }
    @Override
    public boolean isCustomerExist(String name){
        boolean isPresent;
        if(customerRepository.findByName(name).getName().equals(name)){
            isPresent = true;
        } else {
            isPresent = false;
        }
        return isPresent;
    };

    @Override
    public  String login( Customer loginRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getName(), loginRequest.getPassword()));
        String token = jwtUtil.generateToken(loginRequest.getName());
        return token;
    }


}
















