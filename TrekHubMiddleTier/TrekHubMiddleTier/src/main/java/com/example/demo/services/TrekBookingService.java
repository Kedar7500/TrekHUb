package com.example.demo.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.TrekBooking;
import com.example.demo.entities.User;
import com.example.demo.repositories.TrekBookingRepository;

@Service
public class TrekBookingService {
    
	@Autowired
	TrekBookingRepository trekbookrepo;
	
	public TrekBooking save(TrekBooking b)
	{
		return trekbookrepo.save(b);
	}
	
	public TrekBooking getBookingInfo(int id)
	{
		System.out.println(id+"");
		//return regrepo.findById(id);
	
		Optional<TrekBooking> u=trekbookrepo.findById(id);
		
		TrekBooking up=null;
		try
		{
			up=u.get();
		}
		catch(NoSuchElementException e)
		{
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return up;
	}
	
	//Part-Bookings View
		public List<TrekBooking> getBookingsbyTrekkerid(User user_id)
		{
			return trekbookrepo.getBookingsbyTrekkerid(user_id);
		}
}
