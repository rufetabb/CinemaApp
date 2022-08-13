package com.example.tmsproject.controller;

import com.example.tmsproject.dto.ShowTimeDto;
import com.example.tmsproject.model.ShowTime;
import com.example.tmsproject.repository.TheatreRepository;
import com.example.tmsproject.service.CustomerService;
import com.example.tmsproject.service.MovieService;
import com.example.tmsproject.service.ShowTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/date")
public class RestController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private ShowTimeService showTimeService;
    @PostMapping("/date-choose/{id}/{date}")
    public List<ShowTimeDto> filmChoose(Model model, @PathVariable Long id, @PathVariable String date) throws IOException, ParseException {
        var movieDetail = movieService.getMovie(id);
        var showTimeList = showTimeService.findByDate(date, movieDetail.getMovieName());
        model.addAttribute("showTimeList", showTimeList);
//        model.addAttribute("movieDetail", movieDetail);
//        model.addAttribute("val", date);
//        model.addAttribute("user", customerService.findByEmail());
//        model.addAttribute("authorities", customerService.getAllRole());

        return showTimeList;
//        return "/general/buy-ticket";

    }
}
