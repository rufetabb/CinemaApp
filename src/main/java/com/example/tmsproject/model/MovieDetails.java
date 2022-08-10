package com.example.tmsproject.model;

import lombok.*;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
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
    @Temporal(value=TemporalType.DATE)
    @Column(name = "book_on_date")
    private Date bookOnStartDate;
    @Temporal(value=TemporalType.DATE)
    @Column(name = "book_on_finish_date")
    private Date bookOnFinishDate;
    @Temporal(value=TemporalType.TIME)
    private Date clock;
    private String age;
    private String director;
    private String actors;
    private String type;





}
