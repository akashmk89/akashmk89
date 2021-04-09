package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.Placement;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.PlacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class PlacementService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    PlacementRepository placementRepository;
    public Placement addPlacement(byte[] imageBytes) throws ResourceNotFoundException{
            Department department = departmentRepository.findById(22).orElseThrow(() -> new ResourceNotFoundException("Department not found on :: " + 22));
            Placement placement = Placement.builder()
            .studentFullName("akash")
            .salary(500000d)
            .companyName("infosys")
            .designation("software engineer")
            .email("akash.m.konnur@gmail.com")
            .phoneNumber(9164560226l)
            .placementDate(LocalDate.of(2007,12,22))
            .department(department)
            .photo(imageBytes)
            .build();
        placementRepository.save(placement);
        return  placement;
    }

}
