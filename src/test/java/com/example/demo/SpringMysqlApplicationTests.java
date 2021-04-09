package com.example.demo;

import com.example.demo.service.DepartmentService;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringMysqlApplicationTests {
	
	private DepartmentService departmentService;
	
	@BeforeEach                                         
	    public void setUp() throws Exception {
	        departmentService = new DepartmentService();
	    }
//	
//	@Test
//	void contextLoads() {
//	}
	
	@Test
	void checksArrayValue(){
		assertEquals(30,departmentService.multiply(5,6),"answer is 30");
	}
}