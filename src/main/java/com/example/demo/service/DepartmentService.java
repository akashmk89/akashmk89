package com.example.demo.service;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import com.example.demo.Utils.DTOs.GetAllForDropDownDTO;
import com.example.demo.Utils.SearchCriteria.DepartmentPage;
import com.example.demo.Utils.SearchCriteria.DepartmentSearchCriteria;
import com.example.demo.repository.DepartmentCriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.Utils.CSVHelper.CSVHelper;
import com.example.demo.Utils.DTOs.DepartmentAddEditDTO;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.User;

@Service
@CacheConfig(cacheNames={"departments"})
public class DepartmentService {
	public int multiply(int a, int b) {
		return a*b;
	}
	
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private UserRepository userDAO;
	@Autowired
	private DepartmentCriteriaRepository departmentCriteriaRepository;

	public Page<Department> getDepartments(DepartmentPage departmentPage,
										   		DepartmentSearchCriteria departmentSearchCriteria){
	return  departmentCriteriaRepository.findAllWithFilters(departmentPage,departmentSearchCriteria);
	}

	public List<DepartmentAddEditDTO> getDepartmentsWithPagination(Integer pageNo, Integer pageSize, String sortBy){
		List<Department> departments;
		
		List<DepartmentAddEditDTO> departmentsDTO = new ArrayList<DepartmentAddEditDTO>();
		
		PageRequest paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		Page<Department> pagedResult = departmentRepository.findAll(paging);
		if(pagedResult.hasContent()) {
            departments = pagedResult.getContent();
        } else {
           departments = new ArrayList<Department>();
        }
		for(Department department : departments){
			DepartmentAddEditDTO dtoObj = new DepartmentAddEditDTO();
			User hod = department.getHod();
			dtoObj.setId(department.getId());
			dtoObj.setName(department.getName());
			dtoObj.setColor(department.getColor());
			dtoObj.setCode(department.getCode());
			dtoObj.setHodId(department.getHod().getId());
			dtoObj.setHodName(department.getHod().getName());
			dtoObj.setDepartmentMembers(department.getDepartmentMembers());
			departmentsDTO.add(dtoObj);
		}
		return departmentsDTO;
	}

	public List<GetAllForDropDownDTO> getAllForDropDown(){
		return departmentRepository.getAllDepartmentsForDropDown();
//		return new ArrayList<Object>();
	}
	
	public ResponseEntity<List<User>> getAllUsersOfDepartment(Integer id) throws ResourceNotFoundException {
		 Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found on :: " + id));
		 return ResponseEntity.ok().body(department.getDepartmentMembers());
	};

	@Cacheable(value ="departments")
	public List<DepartmentAddEditDTO> getAllDepartment() throws  InterruptedException{
		Thread.sleep(4000);
		List<DepartmentAddEditDTO> departmentsDTO = new ArrayList<DepartmentAddEditDTO>();
		List<Department> departments = (List<Department>) departmentRepository.findAll();
		System.out.println("hi");
		for(Department department : departments){
			DepartmentAddEditDTO dtoObj = new DepartmentAddEditDTO();
			User hod = department.getHod();
			dtoObj.setId(department.getId());
			dtoObj.setName(department.getName());
			dtoObj.setColor(department.getColor());
			dtoObj.setCode(department.getCode());
			dtoObj.setHodId(department.getHod().getId());
			dtoObj.setHodName(department.getHod().getName());
			dtoObj.setDepartmentMembers(department.getDepartmentMembers());
			departmentsDTO.add(dtoObj);
		}
		return departmentsDTO;
	}
	
	public ResponseEntity<Department> getDepartmentById(Integer id) throws ResourceNotFoundException {
		    Department department = departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found on :: " + id));
		    return ResponseEntity.ok().body(department);
	}
	
	@CacheEvict(allEntries=true)
	public Department addDepartment(DepartmentAddEditDTO departmentAddEditDTO)throws ResourceNotFoundException {
		User hod =  userDAO.findById(departmentAddEditDTO.getHodId()).orElseThrow(() -> new ResourceNotFoundException("User with ID Not Found" + departmentAddEditDTO.getHodId()));
		Department departmentToBeAdded = new Department();
		departmentToBeAdded.setName(departmentAddEditDTO.getName());
		departmentToBeAdded.setCode(departmentAddEditDTO.getCode());
		departmentToBeAdded.setColor(departmentAddEditDTO.getColor());
		departmentToBeAdded.setHod(hod); 		
		return departmentRepository.save(departmentToBeAdded);
	}
	
	public Department updateDepartment(DepartmentAddEditDTO departmentAddEditDTO) throws ResourceNotFoundException {
		Department department =  departmentRepository.findById(departmentAddEditDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Department Not Found"));
		User hod =  userDAO.findById(departmentAddEditDTO.getHodId()).orElseThrow(() -> new ResourceNotFoundException("User with ID Not Found" + departmentAddEditDTO.getHodId()));
			department.setName(departmentAddEditDTO.getName());
			department.setColor(departmentAddEditDTO.getColor());
			department.setHod(hod);
			department.setCode(departmentAddEditDTO.getCode());
			return departmentRepository.save(department);
	}
	@CacheEvict(allEntries=true)
	public void deleteDepartment (Integer id) {
	    departmentRepository.deleteById(id);
	  }	
	
	 public void save(MultipartFile file) {
		    try {
		      List<Department> departments = CSVHelper.csvToDepartments(file.getInputStream());
		      departmentRepository.saveAll(departments);
		    } catch (IOException e) {
		      throw new RuntimeException("fail to store csv data: " + e.getMessage());
		    }
		  }
	@Transactional
	public void DoBoth() {
		this.updateDepartment();
		this.UpdateUser();	
	}
	
	public void updateDepartment() {
		
		
	}
	
	public void UpdateUser() {
		
	}
	

}
