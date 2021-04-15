package com.example.demo;

import com.example.demo.Utils.DTOs.GetAllForDropDownDTO;
import com.example.demo.model.Department;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.service.DepartmentService;
//import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;
//import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
//@RunWith(SpringRunner.class)
@SpringBootTest
//@SpringBootTest (classes ={DepartmentService.class})
//@ContextConfiguration(classes = {DepartmentService.class,DepartmentService.class})
public class DepartmentControllerTest {

    @Autowired
    private DepartmentService departmentService;

    @Mock
    private DepartmentRepository departmentRepository;


    @Test
    public void getDepartmentsTest(){
        when(departmentRepository.findAll()).thenReturn(Stream.of(
                new Department( "CS", "ec","ee"),
                new Department("EC","ec","dd"))
                .collect(Collectors.toList()));
        assertEquals(2, departmentService.getDepartmentsWithPagination(0,2,"name").size());
        }
}
