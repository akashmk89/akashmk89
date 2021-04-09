package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.LeaveMasterRepository;
import com.example.demo.model.LeaveMaster;

@Service
public class LeaveMasterService {

	@Autowired
	private LeaveMasterRepository leaveMasterRepository;
	
	public Iterable<LeaveMaster> GetAllLeaves(){
	return	(Iterable<LeaveMaster>) leaveMasterRepository.findAll();
	}
	
	public Optional<LeaveMaster> FindById(int id){
	return leaveMasterRepository.findById(id);
	}
	
	public LeaveMaster AddLeave(LeaveMaster leaveMaster) {
		return leaveMasterRepository.save(leaveMaster);
	}
	
	public LeaveMaster UpdateLeave(LeaveMaster leaveMaster) {
		return leaveMasterRepository.save(leaveMaster);
	}
	
	public void DeleteById(int id) {
		leaveMasterRepository.deleteById(id);
	}
	
	
	
	
}
