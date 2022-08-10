package com.example.tmsproject.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Date {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "date_id")
    private Long dateId;
    @Temporal(value= TemporalType.DATE)
    @Column(name = "book_on_date")
    private java.util.Date bookOnStartDate;
    @Temporal(value=TemporalType.DATE)
    @Column(name = "book_on_finish_date")
    private java.util.Date bookOnFinishDate;
    @Temporal(value=TemporalType.TIME)
    @Column(name = "time")
    @ElementCollection
    @CollectionTable(name = "time",joinColumns = @JoinColumn(name="time_id"))
    private List<java.util.Date> time;
}
