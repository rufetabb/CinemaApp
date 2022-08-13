package com.example.tmsproject.service;

import com.example.tmsproject.dto.ShowTimeDto;
import com.example.tmsproject.mapper.ShowTimeMapper;
import com.example.tmsproject.model.ShowTime;
import com.example.tmsproject.repository.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowTimeService {
    @Autowired
    private ShowTimeRepository showTimeRepository;


//    public List<ShowTimeDto> allShowTime()  {
//        return dateRepository
//                .findAll()
//                .stream()
//                .map(showTime-> ShowTimeMapper
//                        .INSTANCE.entityToDto(showTime))
//                .collect(Collectors.toList());
//    }

    public List<ShowTimeDto> findByDate(String date,String movieName) throws ParseException {
            return showTimeRepository
                    .findByDateAndMovieName(new SimpleDateFormat("yyyy-MM-dd").parse(date),movieName)
                    .stream()
                    .map(showTime-> ShowTimeMapper
                        .INSTANCE.entityToDto(showTime))
                .collect(Collectors.toList());
    }
    public List<ShowTimeDto> getAllMovieForName(String movieName)  {

        return showTimeRepository
                .findByMovieName(movieName)
                .stream()
                .map(showTime-> ShowTimeMapper
                        .INSTANCE.entityToDto(showTime))
                .collect(Collectors.toList());
    }
//    private static List <Date> list=new ArrayList<Date>();
    public void saveShowTime(  String time, String cinema,String date,String movieName) throws ParseException {
         var showTime= showTimeRepository.findByMovieNameAndCinemaAndDate(movieName,cinema,new SimpleDateFormat("yyyy-MM-dd").parse(date));
        if(showTime==null){
            showTime=new ShowTime();
        }
        var  list=showTime.getTime();
          if (list==null){
              list= new ArrayList<>();
          }

         list.add(new SimpleDateFormat("hh:mm").parse(time));
        showTime.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        showTime.setTime(list);
        showTime.setCinema(cinema);
        showTime.setMovieName(movieName);

        showTimeRepository.save(showTime);
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

//   public void saveShowTime2(ShowTime showTime){
//        dateRepository.save(showTime);
//   }
    public ShowTimeDto findById(Long id )  {

           var showTimeDto= ShowTimeMapper
                   .INSTANCE
                   .entityToDto(showTimeRepository
                           .findById(id).get());

        return showTimeDto;
    }
//    public ShowTime findByIdAndTime (Long id,String time) throws ParseException {
//
//        return  dateRepository.findByIdAndTime(id,new SimpleDateFormat("hh:mm").parse(time));
//    }

}
