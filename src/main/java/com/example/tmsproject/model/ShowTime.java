package com.example.tmsproject.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "show_time")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ShowTime {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Temporal(value=TemporalType.TIME)
    @ElementCollection
    @CollectionTable(name="time",joinColumns = @JoinColumn(name="time_id"))
    private List<Date> time;
//    @OneToOne(mappedBy = "showTime")
//    @JoinColumn(name = "seat_id",referencedColumnName = "id")
//    private Seat seat;
    @Temporal(value= TemporalType.DATE)
    private Date date;
    private String cinema;
    private String movieName;
    @Temporal(value= TemporalType.DATE)
    @Column(name = "book_on_date")
    private java.util.Date bookOnStartDate;
    @Temporal(value=TemporalType.DATE)
    @Column(name = "book_on_finish_date")
    private java.util.Date bookOnFinishDate;

}
