package com.example.tmsproject.service;

import com.example.tmsproject.dto.AdminRegistrationDto;
import com.example.tmsproject.dto.CustomerRegistrationDto;
import com.example.tmsproject.model.*;
import com.example.tmsproject.repository.BookingRepository;
import com.example.tmsproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    @Transactional
    public Customer save( CustomerRegistrationDto customerRegistrationDto) {
       Customer customer=new Customer(customerRegistrationDto.getFirstName(),
               customerRegistrationDto.getLastName(),
               customerRegistrationDto.getEmail(),
               new BCryptPasswordEncoder().encode(customerRegistrationDto.getPassword()),
               Arrays.asList(new Role("ROLE_USER")));
       customerRepository.save(customer);
       return customer;
    }
    @Override
    @Transactional
    public Customer save( AdminRegistrationDto adminRegistrationDto) {
        Customer admin=new Customer(adminRegistrationDto.getFirstName(),
                adminRegistrationDto.getLastName(),
                adminRegistrationDto.getEmail(),
                new BCryptPasswordEncoder().encode(adminRegistrationDto.getPassword()),
                Arrays.asList(new Role("ROLE_ADMIN")));
        customerRepository.save(admin);
        return admin;
    }
    @Transactional
    public Optional<Customer> findUserByEmail(String email){
        return customerRepository.findUserByEmail(email);
    }
    @Override
    public Boolean userExists(String email) {
        return customerRepository.findUserByEmail(email).isPresent();
    }


    @Override
    @Transactional
    public UserDetails loadUserByUsername( String email) throws UsernameNotFoundException {
        Customer customer= customerRepository.findByEmail(email);

        if(customer==null){
            throw new UsernameNotFoundException("Username ve password etibarsidir!");

        }
       return new CustomerDetailService(customer);


    }
    public List<Customer> allCustomer(){
        return customerRepository.findByRole("ROLE_USER");
    }
    public List<Customer> allAdmin(){
        return customerRepository.findByRole("ROLE_ADMIN");
    }



}
