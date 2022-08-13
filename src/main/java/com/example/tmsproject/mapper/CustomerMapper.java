package com.example.tmsproject.mapper;


import com.example.tmsproject.dto.CustomerRegistrationDto;
import com.example.tmsproject.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE= Mappers.getMapper(CustomerMapper.class);

    CustomerRegistrationDto entityToDto(Customer customer);
}
