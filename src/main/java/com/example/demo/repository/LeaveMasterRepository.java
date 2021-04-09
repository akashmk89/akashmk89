package com.example.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.model.LeaveMaster;
//import com.example.demo.Utils.enums.LeaveTypes;

public interface LeaveMasterRepository extends PagingAndSortingRepository<LeaveMaster, Integer> {

	
}
