package com.example.tmsproject.mapper;

import com.example.tmsproject.dto.BookingDto;
import com.example.tmsproject.dto.MovieDetailsDto;
import com.example.tmsproject.model.Booking;
import com.example.tmsproject.model.MovieDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookingMapper {
    BookingMapper INSTANCE= Mappers.getMapper(BookingMapper.class);
    @Mappings({
            @Mapping(source = "bid", target = "bid"),
            @Mapping(source = "time", target = "time"),
            @Mapping(source = "seatNo", target = "seatNo"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "cinema", target = "cinema"),
            @Mapping(source = "movieName", target = "movieName"),
            @Mapping(source = "capacity", target = "capacity"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "hall", target = "hall"),
            @Mapping(source = "customer", target = "customer")

    })
    Booking dtoToEntity(BookingDto bookingDto);
    @Mappings({
            @Mapping(source = "bid", target = "bid"),
            @Mapping(source = "time", target = "time"),
            @Mapping(source = "seatNo", target = "seatNo"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "cinema", target = "cinema"),
            @Mapping(source = "movieName", target = "movieName"),
            @Mapping(source = "capacity", target = "capacity"),
            @Mapping(source = "price", target = "price"),
            @Mapping(source = "hall", target = "hall"),
            @Mapping(source = "customer", target = "customer"),
            @Mapping(source = "createdDate", target = "createdDate")
    })
    BookingDto entityToDto(Booking booking);
}
