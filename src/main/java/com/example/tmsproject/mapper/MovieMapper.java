package com.example.tmsproject.mapper;

import com.example.tmsproject.dto.MovieDetailsDto;
import com.example.tmsproject.model.MovieDetails;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovieMapper {
    MovieMapper INSTANCE= Mappers.getMapper(MovieMapper.class);
//    @Mappings({
//            @Mapping(source = "movieId", target = "movieId"),
//            @Mapping(source = "movieName", target = "movieName"),
//            @Mapping(source = "fileImage", target = "fileImage"),
//            @Mapping(source = "movieDetails", target = "movieDetails"),
//            @Mapping(source = "movieTrailer", target = "movieTrailer"),
//            @Mapping(source = "bookOnStartDate", target = "bookOnStartDate"),
//            @Mapping(source = "bookOnFinishDate", target = "bookOnFinishDate"),
//            @Mapping(source = "clock", target = "clock"),
//            @Mapping(source = "age", target = "age"),
//            @Mapping(source = "director", target = "director"),
//            @Mapping(source = "actors", target = "actors"),
//            @Mapping(source = "type", target = "type")
//
//    })
     MovieDetails dtoToEntity (MovieDetailsDto movieDetailsDto);
//    @Mappings({
//           @Mapping(source = "movieId", target = "movieId"),
//            @Mapping(source = "movieName", target = "movieName"),
//            @Mapping(source = "fileImage", target = "fileImage"),
//            @Mapping(source = "movieDetails", target = "movieDetails"),
//            @Mapping(source = "movieTrailer", target = "movieTrailer"),
//            @Mapping(source = "bookOnStartDate", target = "bookOnStartDate"),
//            @Mapping(source = "bookOnFinishDate", target = "bookOnFinishDate"),
//            @Mapping(source = "clock", target = "clock"),
//            @Mapping(source = "age", target = "age"),
//            @Mapping(source = "director", target = "director"),
//            @Mapping(source = "actors", target = "actors"),
//            @Mapping(source = "type", target = "type")
//    })
     MovieDetailsDto entityToDto(MovieDetails movieDetails);
}
