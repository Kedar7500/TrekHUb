package com.example.demo.controllers;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.AddTrek;
import com.example.demo.entities.Login;
import com.example.demo.entities.PassBasedEnc;
import com.example.demo.entities.Role;
import com.example.demo.entities.SaltValue;
import com.example.demo.entities.User;
import com.example.demo.entities.UserReg;
import com.example.demo.services.LoginService;
import com.example.demo.services.RoleService;
import com.example.demo.services.UserRegService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class UserRegController {
	
	@Autowired
	UserRegService uservice;
	
	@Autowired
	LoginService lservice;
	
	@Autowired
	RoleService rservice;
	
	@Autowired
	SaltValue saltValue;
	
	@Autowired
	JavaMailSender sender;
	
	@PostMapping("/userreg")
	public boolean regUser(@RequestBody UserReg ureg)
	{
		boolean flag=true;
		try {
		Role r=rservice.getRole(ureg.getRoleid());
		System.out.println(saltValue.getSalt());
		
		String encrypted=PassBasedEnc.generateSecurePassword(ureg.getPwd(), saltValue.getSalt());
		
		Login l=new Login(ureg.getUid(),encrypted,r);
		
		Login lsaved=lservice.save(l);
		
		User u=new User(ureg.getFname(),ureg.getLname(),ureg.getEmail(),ureg.getContact(),ureg.getAdharno(),ureg.getGender(),ureg.getAddressline(),ureg.getPincode(),lsaved);
	
		User us= uservice.saveUser(u);
		
		SimpleMailMessage mailMsg= new SimpleMailMessage();
		mailMsg.setFrom("onlineopprtunity21@gmail.com");
		mailMsg.setTo(us.getEmail());
		mailMsg.setSubject("Test Mail");
		mailMsg.setText("this is my spring boot test mail adding functionality");
		
		sender.send(mailMsg);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			flag=false;
		}
		return flag;
			
	}
	
	@GetMapping("/guides")
    public List<User> getUsersWithGuideRole() {
        int guideRoleId = 2; 
        return uservice.getUsersByRoleId(guideRoleId);
    }
	

	@GetMapping("/guideid")
    public List<Login> getUserWithGuideId() {
        return uservice.getUsersByRoleId1();
    }
	
	@GetMapping("/trekids")
	public List<AddTrek> getUserWithGuideIds()
	{
		return uservice.getTrekIds();
	}
	
	@GetMapping("/userid/{loginId}")
    public Integer getUserIdByLoginId(@PathVariable String loginId) {
        return uservice.getUserIdByLoginId(loginId);
    }
	
	@GetMapping("profile/{userid}")
	public List<User>getUserIdByLoginid1(@PathVariable int userid)
	{
		return uservice.getUserBYId(userid);
	}
	
	@PostMapping("/updateprofile/{userId}")
    public User updateProfile(@PathVariable int userId, @RequestBody UserReg updatedUser) {
        return uservice.updateUser(userId, updatedUser);
	}
	

}
