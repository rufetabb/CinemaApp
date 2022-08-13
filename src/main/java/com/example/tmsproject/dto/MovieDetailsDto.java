package com.example.tmsproject.dto;

import com.example.tmsproject.model.MovieDetails;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;


public class MovieDetailsDto {
    private Long movieId;
    private String movieName;
    private String fileImage;
    private String movieDetails;
    private String movieTrailer;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookOnStartDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookOnFinishDate;
    private String clock;
    private String age;
    private String director;
    private String actors;
    private String type;
    private MultipartFile multipartFile;

    public MovieDetailsDto() {
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) throws IOException {
        this.multipartFile = multipartFile;
        if (!multipartFile.isEmpty()) {
            this.setFileImage(Base64.getEncoder().encodeToString(multipartFile.getBytes()));
        }
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getFileImage() {

            return fileImage;

    }

    public void setFileImage(String fileImage) {
            this.fileImage = fileImage;
    }

    public String getMovieDetails() {
        return movieDetails;
    }

    public void setMovieDetails(String movieDetails) {
        this.movieDetails = movieDetails;
    }

    public String getMovieTrailer() {
        return movieTrailer;
    }

    public void setMovieTrailer(String movieTrailer) {
        this.movieTrailer = movieTrailer;
    }

    public LocalDate getBookOnStartDate() {
        return bookOnStartDate;
    }

    public void setBookOnStartDate(LocalDate bookOnStartDate) {
        this.bookOnStartDate = bookOnStartDate;
    }

    public LocalDate getBookOnFinishDate() {
        return bookOnFinishDate;
    }

    public void setBookOnFinishDate(LocalDate bookOnFinishDate) {
        this.bookOnFinishDate = bookOnFinishDate;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
