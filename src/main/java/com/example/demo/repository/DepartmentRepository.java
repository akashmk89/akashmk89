package com.example.demo.repository;
import com.example.demo.Utils.DTOs.GetAllForDropDownDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.example.demo.model.Department;

import java.util.List;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Integer> {
    @Query("SELECT new com.example.demo.Utils.DTOs.GetAllForDropDownDTO(d.id, d.name) from Department d")
    public List<GetAllForDropDownDTO> getAllDepartmentsForDropDown();
}

