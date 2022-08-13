package com.example.tmsproject.mapper;

import com.example.tmsproject.dto.MovieDetailsDto;
import com.example.tmsproject.dto.TheatreDto;
import com.example.tmsproject.model.MovieDetails;
import com.example.tmsproject.model.Theatre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TheatreMapper {
    TheatreMapper INSTANCE = Mappers.getMapper(TheatreMapper.class);

        @Mappings({
            @Mapping(source = "theatreId", target = "theatreId"),
            @Mapping(source = "theatreName", target = "theatreName"),
            @Mapping(source = "theatreLocation", target = "theatreLocation"),
            @Mapping(source = "capacity", target = "capacity"),
            @Mapping(source = "ticketPrice", target = "ticketPrice")

    })
    Theatre dtoToEntity(TheatreDto theatreDto);
        @Mappings({
            @Mapping(source = "theatreId", target = "theatreId"),
            @Mapping(source = "theatreName", target = "theatreName"),
            @Mapping(source = "theatreLocation", target = "theatreLocation"),
            @Mapping(source = "capacity", target = "capacity"),
            @Mapping(source = "ticketPrice", target = "ticketPrice")

    })
    TheatreDto entityToDto(Theatre theatre);
}
