package com.example.tmsproject.mapper;

import com.example.tmsproject.dto.ShowTimeDto;
import com.example.tmsproject.dto.TheatreDto;
import com.example.tmsproject.model.ShowTime;
import com.example.tmsproject.model.Theatre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShowTimeMapper {
    ShowTimeMapper INSTANCE= Mappers.getMapper(ShowTimeMapper.class);
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "time", target = "time"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "cinema", target = "cinema"),
            @Mapping(source = "movieName", target = "movieName")

    })
    ShowTimeDto entityToDto(ShowTime showTime);
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "time", target = "time"),
            @Mapping(source = "date", target = "date"),
            @Mapping(source = "cinema", target = "cinema"),
            @Mapping(source = "movieName", target = "movieName")

    })
    ShowTime dtoToEntity(ShowTimeDto showTime);

}
