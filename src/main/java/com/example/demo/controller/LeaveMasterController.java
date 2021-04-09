package com.example.demo.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.LeaveMaster;
import com.example.demo.service.LeaveMasterService;
import com.example.demo.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/leaveMaster")
public class LeaveMasterController {

	@Autowired
	private LeaveMasterService leaveMasterService;
			
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get")
	
	public Iterable<LeaveMaster> getAllLeaves(){
		return leaveMasterService.GetAllLeaves();
	}
		
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/by-id/{id}")
	 public Optional<LeaveMaster> getLeaveById(@PathVariable("id") int id){
	   return leaveMasterService.FindById(id);
	 }
	
	@PostMapping("/add")
	public LeaveMaster AddLeaveMaster(@RequestBody LeaveMaster LeaveMasterToBeAdded){
		return leaveMasterService.AddLeave(LeaveMasterToBeAdded);	
	}
	@PutMapping("/update/{id}")
	public LeaveMaster UpdateLeaveMaster(@RequestBody LeaveMaster LeaveMasterToBeUpdated) throws ResourceNotFoundException{
		return leaveMasterService.UpdateLeave(LeaveMasterToBeUpdated);	
	}
	@DeleteMapping("/delete/{id}")
	public void DeleteLeaveMaster(@PathVariable("id") int id) {
		leaveMasterService.DeleteById(id);
	}

}
