package com.example.demo.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.TrekBooking;
import com.example.demo.entities.User;

@Transactional
@Repository
public interface TrekBookingRepository extends JpaRepository<TrekBooking, Integer> {

	@Query("select b from TrekBooking b where trekker_id = :user_id")
	public List<TrekBooking>getBookingsbyTrekkerid(@Param ("user_id")User user_id);
	
}