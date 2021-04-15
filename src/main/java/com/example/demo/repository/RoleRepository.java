package com.example.demo.repository;

import com.example.demo.Utils.DTOs.GetAllForDropDownDTO;
import com.example.demo.model.Department;
import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Integer> {
//    @Query("SELECT new com.example.demo.Utils.DTOs.GetAllForDropDownDTO(d.id, d.name) from Department d")
//    public List<GetAllForDropDownDTO> getAllDepartmentsForDropDown();
}

