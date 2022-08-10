package com.example.tmsproject.repository;

import com.example.tmsproject.model.Customer;
import com.example.tmsproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
    @Query(value = "select * from customers_roles cr  join customer c on cr.customer_id=c.id join role_db rd on cr.role_id = rd.id and rd.role_name=?1 ",nativeQuery = true)
    List<Customer> findByRole(String role);
    Optional<Customer> findUserByEmail(String email);
//    Boolean existsByEmail(String email);

}
