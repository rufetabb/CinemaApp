package com.example.tmsproject.controller;

import com.example.tmsproject.model.Booking;
import com.example.tmsproject.repository.DateRepository;
import com.example.tmsproject.repository.TheatreRepository;
import com.example.tmsproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping
public class WebController {
    @Autowired
    private CustomerServiceImp customerService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private DateService dateService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private SeatService seatService;


    @GetMapping(path={"/home","/"})
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        var movies = movieService.getAllMovie();
        model.addAttribute("movies",movies);
        model.addAttribute("authorities",roles);
        return "/general/index";
    }

    @GetMapping("/login")
    public String login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "/general/login";
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/";

        }

        return "redirect:/login?logout";
    }
    @GetMapping("/buy-ticket/{id}")
    public String buyTicket(@PathVariable Long id,Model model) throws IOException, ParseException {
         var movieDetail=movieService.getMovie(id);

          var showTimeList= dateService.findByDate(movieDetail.getBookOnStartDate().toString(),movieDetail.getMovieName());
          model.addAttribute("showTimeList",showTimeList);
          model.addAttribute("movieDetail",movieDetail);
        model.addAttribute("val",movieDetail.getBookOnStartDate());
//          model.addAttribute("theatres", theatreRepository.findAll());
        return "/general/buy-ticket";
    }
    @GetMapping("/date-choose/{id}")
    public String filmChoose(Model model,@PathVariable Long id,@RequestParam("sdate") String date ) throws IOException, ParseException {
        var movieDetail=movieService.getMovie(id);
         var showTimeList=dateService.findByDate(date,movieDetail.getMovieName());
        model.addAttribute("showTimeList",showTimeList);
        model.addAttribute("movieDetail",movieDetail);
        model.addAttribute("val",date);
        return "/general/buy-ticket";

    }
    @GetMapping("/showPrice/{id}/{time}")
    public String checkTheatre(@PathVariable(value="id") Long id,@PathVariable(value="time") String time,Model model) throws ParseException {
        var showTime=dateService.findById(id);
        System.out.println(time);

       var theatre= theatreRepository.findByTheatreName(showTime.getCinema());
       model.addAttribute("shTime",showTime);
//       model.addAttribute("booking",new Booking());
       model.addAttribute("movieN",showTime.getMovieName());
        model.addAttribute("theatreName", theatre.getTheatreName());
        model.addAttribute("theatrePrice",theatre.getTicketPrice());
        model.addAttribute("theatreCapacity", theatre.getCapacity());
        model.addAttribute("localTime",time);
        model.addAttribute("date",showTime.getDate());
       var listSeat= seatService.allSeat(showTime,time);

            model.addAttribute("seats",listSeat);


        return "/general/show_price";
    }
    @PostMapping("/book-seat/{id}")
    public String buyTicket(@PathVariable Long id,@RequestParam("mname") String movieName,
                            @RequestParam("thname") String theatreName,
                            @RequestParam("thcapacity") String theatreCapacity,
                            @RequestParam("mdate") String date,
                            @RequestParam("mtime") String time,
                            @RequestParam("mprice") String price,
                            @RequestParam("seatNo") List<String> seat
                            ) throws ParseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();

        for (GrantedAuthority a : authorities) {

            roles.add(a.getAuthority());
        }

           bookingService.saveBooking2(id,movieName,theatreName,theatreCapacity,date,time,price,seat);

       return "redirect:/";
    }

    @GetMapping("/trailer")
    public String trailer(Model model){
        var movieDetails=movieService.getAllMovie();
        model.addAttribute("movieDetails",movieDetails);
     return "/general/trailer";
    }


    @GetMapping("/films")
    public String films(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        var movieDetails=movieService.getAllMovie();
        model.addAttribute("authorities",roles);
        model.addAttribute("movies",movieDetails);
        return "/general/films";
    }
    @GetMapping("/contact")
    public String contact(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        var movieDetails=movieService.getAllMovie();
        model.addAttribute("authorities",roles);

        return "/general/contact";
    }




}
