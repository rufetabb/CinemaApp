package com.example.tmsproject.mapper;

import com.example.tmsproject.dto.SeatDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SeatMapper {
    SeatMapper INSTANCE= Mappers.getMapper(SeatMapper.class);

}
