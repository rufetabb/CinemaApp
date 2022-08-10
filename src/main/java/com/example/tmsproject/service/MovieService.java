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
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private TheatreRepository theatreRepository;

    public void saveMovie(MultipartFile multipartFile, String movieName, String details, String movieTrailer, String movieDate,String movieFinishDate) throws IOException, ParseException {
        MovieDetails movieDetails = new MovieDetails();
        movieDetails.setMovieName(movieName);
        movieDetails.setMovieDetails(details);
        movieDetails.setMovieTrailer(movieTrailer);
        movieDetails.setFileImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        movieDetails.setBookOnStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(movieDate));
        movieDetails.setBookOnFinishDate    (new SimpleDateFormat("yyyy-MM-dd").parse(movieFinishDate));
        movieRepository.save(movieDetails);
    }

    public MovieDetails getMovieId(Long id) {
        return movieRepository.findById(id).get();
    }

    public List<MovieDetails> getAllMovie() {
        return movieRepository.findAll();
    }

    public MovieDetails updateMovie(Long id, MultipartFile multipartFile,
                                    String movieName,String movieDetails,
                                    String movieTrailer,String movieDate,
                                    String movieFinishDate) throws IOException, ParseException {
        var movieExists = movieRepository.existsById(id);

        if (!movieExists) new RuntimeException("movie not found");
        var movie = movieRepository.findById(id).get();
          movie.setMovieName(movieName);
          movie.setMovieDetails(movieDetails);
          movie.setMovieTrailer(movieTrailer);
          movie.setBookOnStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(movieDate));
          movie.setBookOnFinishDate(new SimpleDateFormat("yyyy-MM-dd").parse(movieFinishDate));
          if(!multipartFile.isEmpty()) {
              movie.setFileImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
          }
          movieRepository.save(movie);
        return movie;

    }
    public MovieDetails getMovie(Long id) throws IOException {
        var movieExists = movieRepository.existsById(id);
        if (!movieExists) new RuntimeException("movie not found");
        var movie = movieRepository.findById(id).get();
        return movie;

    }
    public List<MovieDetails> getAllMovieName(String name) throws IOException {
        var movie = movieRepository.findByMovieName(name);
        return movie;

    }

    public void deleteMovie(Long id) {
        var movieExists = movieRepository.existsById(id);
        if (!movieExists) new RuntimeException("movie not found");
        movieRepository.deleteById(id);

    }
    public void saveTheatre(Theatre theatre){
        theatreRepository.save(theatre);



    }

}
