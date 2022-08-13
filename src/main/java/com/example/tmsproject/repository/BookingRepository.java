package com.example.tmsproject.repository;

import com.example.tmsproject.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking,Long> {

    Optional<Booking> findByMovieNameAndCinemaAndDateAndTime(String name, String cinema, Date date, Date time);
    List<Booking> findByCustomer(String email);
}
