package com.example.tmsproject.service;

import com.example.tmsproject.model.Theatre;
import com.example.tmsproject.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;

    public List<Theatre> getAllTheatre(){
        return theatreRepository.findAll();
    }
}
