package com.example.demo.controller;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.PlacementRepository;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.PlacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequestMapping("/api/placement")
public class PlacementController {

    @Autowired
    PlacementService placementService;
    @Autowired
    DepartmentService departmentService;
    @Autowired
    PlacementRepository placementRepository;

    @PostMapping("/upload")
    public ResponseEntity AddPlacement(@RequestParam("file") MultipartFile file) throws IOException, ResourceNotFoundException {
        byte[] image = file.getBytes();
       return new ResponseEntity<>(placementService.addPlacement(image), HttpStatus.OK);
    }
    @GetMapping("/get")
    public ResponseEntity GetPlacements() {
           return new ResponseEntity<>(placementRepository.findAll(),HttpStatus.OK);
    }
    @PutMapping
    public ResponseEntity UpdatePlacement(){
        return new ResponseEntity<>(placementRepository.findAll(), HttpStatus.OK);
    }
    @DeleteMapping("/delete")
    void deleteEmployee(@RequestParam("id") Integer id) throws ResourceNotFoundException {
        departmentService.deleteDepartment(id);
    }

}
