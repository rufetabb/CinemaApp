package com.example.tmsproject.service;

import com.example.tmsproject.dto.BookingDto;
import com.example.tmsproject.mapper.BookingMapper;
import com.example.tmsproject.mapper.CustomerMapper;
import com.example.tmsproject.model.Booking;
import com.example.tmsproject.repository.BookingRepository;
import com.example.tmsproject.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService  {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ShowTimeRepository showTimeRepository;
    @Autowired
    private ShowTimeService showTimeService;
    @Autowired
    private SeatService seatService;





    //    public void saveBooking(Booking booking){
//        bookingRepository.save(booking);
//    }
    public void saveBooking2(Long id,String movieName,
                             String theatreName,
                             String theatreCapacity,
                             String date,
                             String time,
                             String price,
                             List<String> seat) throws ParseException {
       Object principal= SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal). getUsername();
        } else {
             username = principal. toString();
        }
        seatService.seatSave(id,time,seat);
        var booking =new Booking();
        booking.setCinema(theatreName);
        booking.setSeatNo(seat);
        booking.setCapacity(Integer.parseInt(theatreCapacity));
        booking.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        booking.setTime(new SimpleDateFormat("hh:mm").parse(time));
        booking.setMovieName(movieName);
        booking.setPrice(Double.parseDouble(price));
        booking.setCustomer(username);



          bookingRepository.save(booking);
    }
    public List<BookingDto>  getBookingByEmail(){
        Object principal= SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal). getUsername();
        } else {
            username = principal. toString();
        }
        return bookingRepository
                .findByCustomer(username)
                .stream()
                .map(booking -> BookingMapper
                        .INSTANCE.entityToDto(booking)).collect(Collectors.toList());
    }
//    public Booking getBooking(String movieName,String cinema,Date date, String time) throws ParseException {
//        return bookingRepository.findByMovieNameAndCinemaAndDateAndTime(movieName,cinema,date,new SimpleDateFormat("hh:mm").parse(time)).orElse(new Booking());
//    }
    public List<BookingDto> getAllBooking(){

        return bookingRepository
                .findAll()
                .stream()
                .map(booking-> BookingMapper.INSTANCE.entityToDto(booking))
                .collect(Collectors.toList());
    }


}
