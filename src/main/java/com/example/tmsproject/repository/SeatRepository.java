package com.example.tmsproject.repository;

import com.example.tmsproject.model.Seat;
import com.example.tmsproject.model.ShowTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
@Repository
public interface SeatRepository extends JpaRepository<Seat,Long> {
   @Query(value="select* from all_seat a where a.show_id=?1 and a.time=?2 ",nativeQuery = true)
    Seat findByIdAndTime(Long id, Date time);
}
