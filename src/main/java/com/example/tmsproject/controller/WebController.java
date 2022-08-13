package com.example.tmsproject.controller;

import com.example.tmsproject.repository.TheatreRepository;
import com.example.tmsproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping
public class WebController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private TheatreRepository theatreRepository;
    @Autowired
    private ShowTimeService showTimeService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private SeatService seatService;


    @GetMapping(path = {"/home", "/"})
    public String index(Model model) {

        var movies = movieService.getAllMovie();
        model.addAttribute("movies", movies);
        model.addAttribute("authorities", customerService.getAllRole());
        model.addAttribute("user", customerService.findByEmail());


        return "/general/index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "/general/login";
        }
        model.addAttribute("user", customerService.findByEmail());
        model.addAttribute("authorities", customerService.getAllRole());
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "redirect:/";

        }
        model.addAttribute("user", customerService.findByEmail());
        model.addAttribute("authorities", customerService.getAllRole());
        return "redirect:/login?logout";
    }

    @GetMapping("/buy-ticket/{id}")
    public String buyTicket(@PathVariable Long id, Model model) throws IOException, ParseException {
        var movieDetail = movieService.getMovie(id);

        var showTimeList = showTimeService.findByDate(movieDetail.getBookOnStartDate().toString(), movieDetail.getMovieName());
        model.addAttribute("showTimeList", showTimeList);
        model.addAttribute("movieDetail", movieDetail);
        model.addAttribute("val", movieDetail.getBookOnStartDate());
        model.addAttribute("user", customerService.findByEmail());
        model.addAttribute("authorities", customerService.getAllRole());
//          model.addAttribute("theatres", theatreRepository.findAll());
        return "/general/buy-ticket";
    }



    @GetMapping("/buy-ticket/showPrice/{id}/{time}")
    public String checkTheatre(@PathVariable(value = "id") Long id, @PathVariable(value = "time") String time, Model model) throws ParseException {
        var showTime = showTimeService.findById(id);
        System.out.println(time);

        var theatre = theatreRepository.findByTheatreName(showTime.getCinema());
        model.addAttribute("shTime", showTime);
//       model.addAttribute("booking",new Booking());
        model.addAttribute("movieN", showTime.getMovieName());
        model.addAttribute("theatreName", theatre.getTheatreName());
        model.addAttribute("theatrePrice", theatre.getTicketPrice());
        model.addAttribute("theatreCapacity", theatre.getCapacity());
        model.addAttribute("localTime", time);
        model.addAttribute("date", showTime.getDate());
        model.addAttribute("user", customerService.findByEmail());
        model.addAttribute("authorities", customerService.getAllRole());
        var listSeat = seatService.allSeat(showTime, time);

        model.addAttribute("seats", listSeat);


        return "/general/show_price";
    }

    @PostMapping("/book-seat/{id}")
    public String buyTicket(Model model, @PathVariable Long id, @RequestParam("mname") String movieName,
                            @RequestParam("thname") String theatreName,
                            @RequestParam("thcapacity") String theatreCapacity,
                            @RequestParam("mdate") String date,
                            @RequestParam("mtime") String time,
                            @RequestParam("mprice") String price,
                            @RequestParam("seatNo") List<String> seat
    ) throws ParseException {
        model.addAttribute("user", customerService.findByEmail());
        model.addAttribute("authorities", customerService.getAllRole());
        bookingService.saveBooking2(id, movieName, theatreName, theatreCapacity, date, time, price, seat);

        return "redirect:/";
    }

    @GetMapping("/trailer")
    public String trailer(Model model) {
        var movieDetails = movieService.getAllMovie();
        model.addAttribute("movieDetails", movieDetails);
        model.addAttribute("user", customerService.findByEmail());
        model.addAttribute("authorities", customerService.getAllRole());
        return "/general/trailer";
    }


    @GetMapping("/films")
    public String films(Model model) {

        var movieDetails = movieService.getAllMovie();

        model.addAttribute("movies", movieDetails);
        model.addAttribute("authorities", customerService.getAllRole());
        model.addAttribute("user", customerService.findByEmail());
        return "/general/films";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("authorities", customerService.allCustomer());
        model.addAttribute("user", customerService.findByEmail());
        model.addAttribute("authorities", customerService.getAllRole());

        return "/general/contact";
    }

    @GetMapping("/customer-booking")
    public String customerBooking(Model model) {
        model.addAttribute("user", customerService.findByEmail());
        model.addAttribute("authorities", customerService.getAllRole());
        model.addAttribute("bookings", bookingService.getBookingByEmail());
        return "/general/customer-booking";


    }


}
