package com.example.tmsproject.service;

import com.example.tmsproject.dto.AdminRegistrationDto;
import com.example.tmsproject.dto.CustomerRegistrationDto;
import com.example.tmsproject.model.*;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CustomerService extends UserDetailsService {
    Customer save (CustomerRegistrationDto customerRegistrationDto);
    Customer save (AdminRegistrationDto adminRegistrationDto);
    Boolean userExists(String email);


}
