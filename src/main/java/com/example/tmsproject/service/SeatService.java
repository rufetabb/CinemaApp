package com.example.tmsproject.service;

import com.example.tmsproject.dto.ShowTimeDto;
import com.example.tmsproject.mapper.ShowTimeMapper;
import com.example.tmsproject.model.Seat;
import com.example.tmsproject.model.ShowTime;
import com.example.tmsproject.repository.SeatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;
    @Autowired
    private ShowTimeService showTimeService;
    public List<String> allSeat(ShowTimeDto showTime, String time ) throws ParseException {
        var seat= seatRepository.findByIdAndTime(showTime.getId(),new SimpleDateFormat("hh:mm").parse(time));
        if(seat==null){
            seat=new Seat();
        }
        var seatList= seat.getBookingAllSeat();
        if(seatList==null){
            seatList=new ArrayList<>();
        }
        return seatList;
    }
    public void seatSave(Long id, String time, List<String> list) throws ParseException {
        var showTime= showTimeService.findById(id);
        var seat= seatRepository.findByIdAndTime(showTime.getId(),new SimpleDateFormat("hh:mm").parse(time));
        if(seat==null){
            seat=new Seat();
        }
        var seatList= seat.getBookingAllSeat();
        if(seatList==null){
            seatList=new ArrayList<>();
        }
       var finalSeatList = seatList;
        list.stream().forEach(s-> finalSeatList.add(s));

        seat.setShowTime(ShowTimeMapper.INSTANCE.dtoToEntity(showTime));
        seat.setBookingAllSeat(finalSeatList);
        seat.setTime(new SimpleDateFormat("hh:mm").parse(time));
        seatRepository.save(seat);
    }
}
