package com.example.tmsproject.service;

import com.example.tmsproject.dto.TheatreDto;
import com.example.tmsproject.mapper.TheatreMapper;
import com.example.tmsproject.model.Theatre;
import com.example.tmsproject.repository.TheatreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheatreService {

    @Autowired
    private TheatreRepository theatreRepository;
    

    public List<TheatreDto> getAllTheatre(){
       var listTheatreDto= theatreRepository.
                findAll().
                stream().
                map(theatre ->TheatreMapper.INSTANCE.entityToDto(theatre) ).collect(Collectors.toList());
        return listTheatreDto ;
    }
    public void saveTheatre(TheatreDto theatreDto){
       var theatre= TheatreMapper.INSTANCE.dtoToEntity(theatreDto);
        theatreRepository.save(theatre);
    }
}
