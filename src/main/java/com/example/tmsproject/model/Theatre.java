package com.example.tmsproject.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "theatre")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "theatre_id")
    private Long theatreId;
    @Column(name = "theatre_name")
    private String theatreName;
    @Column(name = "theatre_location")
    private String theatreLocation;
    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "ticket_price")
    private Double ticketPrice;
}
