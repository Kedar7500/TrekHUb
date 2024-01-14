package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.AddTrek;
import com.example.demo.entities.PlanTrek;
import com.example.demo.entities.PlanTrekDummy;
import com.example.demo.entities.User;
import com.example.demo.services.AddTrekService;
import com.example.demo.services.PlanTrekService;
import com.example.demo.services.UserRegService;

@RestController
public class PlanTrekController
{
	@Autowired
	PlanTrekService plantrekserv;
	
	@Autowired
	UserRegService uregservice;
	
	@Autowired
	AddTrekService addtrekserv;
    
	@PostMapping("/addplantrek")
	public PlanTrek addplantrek(@RequestBody PlanTrekDummy trekdummy)
	{
		System.out.println("--------------");
		System.out.println(trekdummy);
		
		System.out.println("--------------"+trekdummy.getTrek_id());
		
		User user= uregservice.getUserInfo(trekdummy.getGuide_id());
		System.out.println(user);
		
		AddTrek adk=addtrekserv.getTrekInfo(trekdummy.getTrek_id());
		
		PlanTrek plantrek=new PlanTrek(trekdummy.getStart_date(),trekdummy.getEnd_date(),trekdummy.getPrice(),
				trekdummy.getAvail_seats(),trekdummy.getLast_date_apply(),adk,user,trekdummy.getStatus());
		
		System.out.println("*********"+adk+"**************");
		
		PlanTrek demo=plantrekserv.save(plantrek);
		
		System.out.println("in plantrek controller"+demo);
		
		return demo;
		
	}
}

