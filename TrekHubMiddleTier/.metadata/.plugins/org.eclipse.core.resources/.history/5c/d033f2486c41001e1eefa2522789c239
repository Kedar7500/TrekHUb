package com.example.demo.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.PlanTrek;

@Transactional
@Repository
public interface PlanTrekRepository extends JpaRepository<PlanTrek, Integer> 
{
	@Query("Select e from PlanTrek e where e.trekidobj.trek_id=:id")
	public PlanTrek getPlantrekbyid(int id);
	

}
