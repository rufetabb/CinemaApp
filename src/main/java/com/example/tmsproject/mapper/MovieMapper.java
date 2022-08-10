package com.example.tmsproject.mapper;

import com.example.tmsproject.dto.MovieDetailsDto;
import com.example.tmsproject.model.MovieDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;
import org.springframework.web.bind.annotation.Mapping;
@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE= Mappers.getMapper(MovieMapper.class);

    public MovieDetails entityToDto(MovieDetailsDto movieDetailsDto);
}
