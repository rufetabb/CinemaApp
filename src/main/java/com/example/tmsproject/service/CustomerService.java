package com.example.tmsproject.service;

import com.example.tmsproject.dto.AdminRegistrationDto;
import com.example.tmsproject.dto.CustomerRegistrationDto;
import com.example.tmsproject.mapper.CustomerMapper;
import com.example.tmsproject.model.*;
import com.example.tmsproject.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.*;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class CustomerService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;


    @Transactional
    public void save( CustomerRegistrationDto customerRegistrationDto) {
        Customer customer=new Customer(customerRegistrationDto.getFirstName(),
                customerRegistrationDto.getLastName(),
                customerRegistrationDto.getEmail(),
                new BCryptPasswordEncoder().encode(customerRegistrationDto.getPassword()),
                Arrays.asList(new Role("ROLE_USER")));
        customerRepository.save(customer);

    }
    @Transactional
    public void save( AdminRegistrationDto adminRegistrationDto) {
        Customer admin=new Customer(adminRegistrationDto.getFirstName(),
                adminRegistrationDto.getLastName(),
                adminRegistrationDto.getEmail(),
                new BCryptPasswordEncoder().encode(adminRegistrationDto.getPassword()),
                Arrays.asList(new Role("ROLE_ADMIN")));

        customerRepository.save(admin);
    }
    //    @Transactional
//    public Optional<Customer> findUserByEmail(String email){
//        return customerRepository.findUserByEmail(email);
//    }

    public Boolean userExists(String email) {
        return customerRepository.findUserByEmail(email).isPresent();
    }
    public CustomerRegistrationDto findByEmail(){
        Object principal= SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal). getUsername();
        } else {
            username = principal.toString();
        }
        if(username=="anonymousUser"){
            return new CustomerRegistrationDto("User","User","anonymousUser");
        }else
        return  CustomerMapper.INSTANCE.entityToDto(customerRepository.findByEmail(username)) ;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer= customerRepository.findByEmail(email);

        if(customer==null){
            throw new UsernameNotFoundException("Username ve password etibarsidir!");

        }
        return new CustomerDetailService(customer);


    }
    public List<CustomerRegistrationDto> allCustomer(){
        return customerRepository.findByRole("ROLE_USER")
                .stream()
                .map(customer -> CustomerMapper.INSTANCE.entityToDto(customer))
                .collect(Collectors.toList());
    }
    public List<CustomerRegistrationDto> allAdmin(){
       return customerRepository.findByRole("ROLE_ADMIN")
               .stream()
               .map(customer -> CustomerMapper.INSTANCE.entityToDto(customer))
               .collect(Collectors.toList());
    }


    public List<String> getAllRole(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        return roles;
    }





}
