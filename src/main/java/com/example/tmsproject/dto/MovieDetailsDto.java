package com.example.tmsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieDetailsDto {

    private Long movieId;
    private String movieName;
    private String fileImage;
    private String movieDetails;
    private String movieTrailer;


}
