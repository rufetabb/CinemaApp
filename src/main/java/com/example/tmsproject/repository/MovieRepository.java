package com.example.tmsproject.repository;

import com.example.tmsproject.model.MovieDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<MovieDetails,Long> {
    List<MovieDetails> findByMovieName(String name);
}
