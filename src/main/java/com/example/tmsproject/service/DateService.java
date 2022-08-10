package com.example.tmsproject.service;

import com.example.tmsproject.model.ShowTime;
import com.example.tmsproject.repository.DateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DateService {
    @Autowired
    private DateRepository dateRepository;
    public List<ShowTime> allShowTime()  {
        return dateRepository.findAll();
    }

//    public List<String> allSeat(Long id,String time) throws ParseException {
//
//        var showTime=dateRepository.findByIdAndTime(id,new SimpleDateFormat("hh:mm").parse(time));
//        if(showTime==null){
//            showTime=new ShowTime();
//        }
//        var listSeat =showTime.getBookingAllSeat();
//        if(listSeat==null){
//            listSeat=new ArrayList<>();
//        }
//        return listSeat;
//    }
    public List<ShowTime> findByDate(String date,String movieName) throws ParseException {
            return dateRepository.findByDateAndMovieName(new SimpleDateFormat("yyyy-MM-dd").parse(date),movieName);
    }
    public List<ShowTime> getAllMovieForName(String movieName)  {
        return dateRepository.findByMovieName(movieName);
    }
//    private static List <Date> list=new ArrayList<Date>();
    public ShowTime saveShowTime(  String time, String cinema,String date,String movieName) throws ParseException {

         var showTime=dateRepository.findByMovieNameAndCinemaAndDate(movieName,cinema,new SimpleDateFormat("yyyy-MM-dd").parse(date));
        if(showTime==null){
            showTime=new ShowTime();
        }
        var  list=showTime.getTime();
          if (list==null){
              list= new ArrayList<>();
          }

         list.add(new SimpleDateFormat("hh:mm").parse(time));
//        showTime.setMovieId(movieId);
        showTime.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        showTime.setTime(list);
        showTime.setCinema(cinema);
        showTime.setMovieName(movieName);

        return dateRepository.save(showTime);
    }
//    public void seatShowTime(Long id,String time ,List<String> seatList) throws ParseException {
//
//        var showTime=  dateRepository.findByIdAndTime(id,new SimpleDateFormat("hh:mm").parse(time));
//        if(showTime==null){
//            showTime=new ShowTime();
//        }
//        var list=showTime.getBookingAllSeat();
//        if (list==null){
//            list= new ArrayList<>();
//        }
//        var finalList = list;
//        seatList.stream().forEach(s-> finalList.add(s));
//        showTime.setBookingAllSeat(finalList);
//           saveShowTime2(showTime);
//    }

   public void saveShowTime2(ShowTime showTime){
        dateRepository.save(showTime);
   }
    public ShowTime findById(Long id )  {

           var showTime= dateRepository.findById(id).get();

        return showTime;
    }
    public ShowTime findByIdAndTime (Long id,String time) throws ParseException {

        return  dateRepository.findByIdAndTime(id,new SimpleDateFormat("hh:mm").parse(time));
    }

}
