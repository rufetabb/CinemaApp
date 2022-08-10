package com.example.tmsproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "booking")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long bid;
    @Temporal(value= TemporalType.TIME)
   private java.util.Date time;
    @ElementCollection
    @CollectionTable(name="seat",joinColumns = @JoinColumn(name="seat_id"))
    private List<String> seatNo;
    @Temporal(value= TemporalType.DATE)
    private Date date;
    private String cinema;
    private String movieName;
    private Integer capacity;
    private Double price;
    private String hall;
    private String customer;


    public Booking(Date time, List<String> seatNo, Date date, String cinema, String movieName, Integer capacity, Double price, String hall) {
        this.time = time;
        this.seatNo = seatNo;
        this.date = date;
        this.cinema = cinema;
        this.movieName = movieName;
        this.capacity = capacity;
        this.price = price;
        this.hall = hall;
    }
}
