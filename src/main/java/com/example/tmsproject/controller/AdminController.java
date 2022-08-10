package com.example.tmsproject.controller;

import com.example.tmsproject.dto.AdminRegistrationDto;
import com.example.tmsproject.model.ShowTime;
import com.example.tmsproject.model.Theatre;
import com.example.tmsproject.repository.DateRepository;
import com.example.tmsproject.repository.TheatreRepository;
import com.example.tmsproject.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.Base64;

@Controller
@RequestMapping("/admin")
public class AdminController {
     @Autowired
    private CustomerServiceImp userService;
    @Autowired
    private MovieService movieService;
    @Autowired
    private TheatreService theatreService;
    @Autowired
    private DateService dateService;
    @Autowired
    private BookingService bookingService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin(Model model) {
        model.addAttribute("films",movieService.getAllMovie());
        model.addAttribute("cinemas",theatreService.getAllTheatre());
        model.addAttribute("bookings",bookingService.getAllBooking());
        model.addAttribute("customers",userService.allCustomer());

        return "/admin/admin";
    }
    @GetMapping("/registration")
    public String showRegistrationAdminForm(Model model){
        model.addAttribute("admin",new AdminRegistrationDto());

        return "/admin/registration-admin";
    }

    @PostMapping("/add-admin")
    public String registerAdminAccount(@Valid @ModelAttribute("admin") AdminRegistrationDto adminRegistrationDto, BindingResult bindingResult){

        if(userService.userExists(adminRegistrationDto.getEmail())){
            bindingResult.addError(new FieldError("admin","email","Email istifadə edilmişdir"));
        }
        if(adminRegistrationDto.getPassword()!=null && adminRegistrationDto.getrPassword()!=null){
            if(!adminRegistrationDto.getPassword().equals(adminRegistrationDto.getrPassword())){
                bindingResult.addError(new FieldError("admin","rPassword","şifrə eyni deyil"));
            }
        }
        if(bindingResult.hasErrors()){
            return "/admin/registration-admin";
        }else
            userService.save(adminRegistrationDto);
        return "redirect:/admin/registration?success";
    }
    @GetMapping("/film-add")
    public String filmAdd(Model model) {
        var movies = movieService.getAllMovie();
        model.addAttribute("movies", movies);

        return "/admin/film-add";
    }


    @PostMapping("/save")
    public String Save(@RequestParam("fileImage") MultipartFile multipartFile,
                       @RequestParam("mname") String movieName,
                       @RequestParam("mdetails") String movieDetails,
                       @RequestParam("mtrailer")  String movieTrailer,
                       @RequestParam("mStartDate") String movieDate,
                       @RequestParam("mFinishDate") String movieFinishDate) throws IOException, ParseException {
        movieService.saveMovie(multipartFile,movieName,movieDetails,movieTrailer,movieDate,movieFinishDate);
        return "redirect:/admin/film-add";
    }
    @GetMapping("/edit/{id}")
    public String showUpdate(Model model,@PathVariable Long id )  {
        var movie = movieService.getMovieId(id);
        model.addAttribute("movieD",movie);
        return "/admin/update";
    }
    @PostMapping("/update/{id}")
    public String updateEmployee(Model model,@PathVariable Long id,
                                 @RequestParam("file") MultipartFile multipartFile,
                                 @RequestParam("mname") String movieName,
                                 @RequestParam("mdetails") String movieDetails,
                                 @RequestParam("mtrailer")  String movieTrailer,
                                 @RequestParam("mStartDate") String movieDate,
                                 @RequestParam("mFinishDate") String movieFinishDate) throws IOException, ParseException {
        var movie = movieService.updateMovie(id,multipartFile,movieName,movieDetails,movieTrailer,movieDate,movieFinishDate);

        return "redirect:/admin/film-add";
    }
    @GetMapping("/delete/{id}")
    public String  deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return "redirect:/admin/film-add";
    }
    @GetMapping("/theatre")
    public String theatreAdd(Model model){
         model.addAttribute("theatre",new Theatre());
         model.addAttribute("theatreList",theatreService.getAllTheatre());
        return "/admin/theatre";
    }

    @GetMapping("/film-date")
    public String filmDate(Model model){
        model.addAttribute("movies",movieService.getAllMovie());
        return "/admin/film-date";

    }
    @GetMapping("/film-config/{id}")
    public String filmConfigById(@PathVariable Long id, Model model) throws IOException {
        var movie=movieService.getMovieId(id);
        var movies=movieService.getAllMovie();
        model.addAttribute("showTimes",dateService.getAllMovieForName(movie.getMovieName()));
        model.addAttribute("mDetail",movie);
        model.addAttribute("mDetails",movies);
        model.addAttribute("thDetails",theatreService.getAllTheatre());
        return "/admin/film-config";

    }


    @PostMapping("/film-save/{id}")
    public String filmConfig(@PathVariable Long id,@RequestParam("stime") String time,
                             @RequestParam("scinema") String cinema,
                             @RequestParam("sdate") String date,
                             @RequestParam("sthName") String name) throws ParseException {
        dateService.saveShowTime(time,cinema,date,name);
        return "redirect:/admin/film-config/"+id;

    }

    @PostMapping("/save-theatre")
    public String movieSave(@ModelAttribute("theatre")Theatre theatre)  {
        movieService.saveTheatre(theatre);
        return "redirect:/admin/theatre";
    }
    @GetMapping("/booking")
    public String booking(Model model) {
     model.addAttribute("bookings",bookingService.getAllBooking());
        return "/admin/booking";

    }
    @GetMapping("/customers")
    public String customers(Model model) {
        model.addAttribute("customers",userService.allCustomer());
        return "/admin/customers";

    }
    @GetMapping("/admin-user")
    public String adminUser(Model model) {
        model.addAttribute("admins",userService.allAdmin());
        return "/admin/user_admin";

    }

}
