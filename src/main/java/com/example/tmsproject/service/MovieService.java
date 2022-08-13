package com.example.tmsproject.service;

import com.example.tmsproject.dto.MovieDetailsDto;
import com.example.tmsproject.mapper.MovieMapper;
import com.example.tmsproject.model.MovieDetails;
import com.example.tmsproject.model.Theatre;
import com.example.tmsproject.repository.MovieRepository;
import com.example.tmsproject.repository.TheatreRepository;
import com.example.tmsproject.utility.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheatreRepository theatreRepository;

    public void saveMovie(MovieDetailsDto movieDetailsDto) throws ParseException {


       var movieDetails=MovieMapper.INSTANCE.dtoToEntity(movieDetailsDto);
        movieRepository.save(movieDetails);
    }

    public MovieDetailsDto getMovieId(Long id) {
        return MovieMapper.INSTANCE.entityToDto(movieRepository.findById(id).get());
    }

    public List<MovieDetailsDto> getAllMovie() {


        return movieRepository.findAll()
                .stream()
                .map(movieDetails -> MovieMapper.INSTANCE.entityToDto(movieDetails))
                .collect(Collectors.toList());
    }

    public void updateMovie(Long id,
                                    MovieDetailsDto movieDetailsDto)  {
        var movieExists = movieRepository.existsById(id);

        if (!movieExists) new RuntimeException("movie not found");
        var movie = movieRepository.findById(id).get();
        if(movieDetailsDto.getFileImage()==null){
            movieDetailsDto.setFileImage(movie.getFileImage());
        }


        var movieDetails=  MovieMapper.INSTANCE.dtoToEntity(movieDetailsDto);
        movieRepository.save(movieDetails);

    }
    public MovieDetailsDto getMovie(Long id) throws IOException {
        var movieExists = movieRepository.existsById(id);
        if (!movieExists) new RuntimeException("movie not found");
        var movie = movieRepository.findById(id).get();
        var movieDto=  MovieMapper.INSTANCE.entityToDto(movie);
        return movieDto;

    }
    public List<MovieDetailsDto> getAllMovieName(String name) throws IOException {
        var movie = movieRepository.findByMovieName(name);
        var movieDto= movie.stream().map(movieDetails ->MovieMapper.INSTANCE.entityToDto(movieDetails) ).collect(Collectors.toList()) ;
        return movieDto;

    }

    public void deleteMovie(Long id) {
        var movieExists = movieRepository.existsById(id);
        if (!movieExists) new RuntimeException("movie not found");
        movieRepository.deleteById(id);

    }


}
