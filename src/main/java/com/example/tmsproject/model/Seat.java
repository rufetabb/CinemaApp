package com.example.tmsproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "all_seat")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Seat {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "show_id",referencedColumnName = "id")
    private ShowTime showTime;
    private Date time;
    @ElementCollection
    @CollectionTable(name="booking_all_seat",joinColumns = @JoinColumn(name="seat_id"))
    private List<String> bookingAllSeat;
}
