package com.example.tmsproject.repository;

import com.example.tmsproject.model.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre,Long> {
      Theatre findByTheatreName(String theatreName);

}
