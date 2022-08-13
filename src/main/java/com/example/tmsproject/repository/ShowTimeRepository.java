package com.example.tmsproject.repository;

import com.example.tmsproject.model.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ShowTimeRepository extends JpaRepository<ShowTime,Long> {

    List<ShowTime> findByMovieName(String movieName);
    List<ShowTime> findByDateAndMovieName(Date date, String movieName);
    ShowTime findByMovieNameAndCinemaAndDate(String movieName,String cinema,Date date);

    ShowTime findByIdAndTime(Long id,Date time);

}
