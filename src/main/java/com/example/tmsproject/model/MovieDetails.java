package com.example.tmsproject.model;

import com.example.tmsproject.dto.MovieDetailsDto;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movie_details")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class MovieDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "movie_id")
    private Long movieId;
    @Column(name = "movie_name", nullable = false)
    private String movieName;
    @Lob
    @Column(name = "file_image")
    private String fileImage;
    @Column(columnDefinition = "varchar(1000)", name = "movie_details", nullable = false)
    private String movieDetails;
    @Column(name = "movie_trailer", nullable = false)
    private String movieTrailer;
    //    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "book_on_date")
    private LocalDate bookOnStartDate;
    //    @Temporal(value = TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "book_on_finish_date")
    private LocalDate bookOnFinishDate;
    private String clock;
    private String age;
    private String director;
    private String actors;
    private String type;

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
