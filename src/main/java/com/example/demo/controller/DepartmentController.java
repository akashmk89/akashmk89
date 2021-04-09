package com.example.demo.controller;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.example.demo.Utils.DTOs.GetAllForDropDownDTO;
import com.example.demo.Utils.SearchCriteria.DepartmentPage;
import com.example.demo.Utils.SearchCriteria.DepartmentSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import com.example.demo.Utils.CSVHelper.CSVHelper;
import com.example.demo.Utils.CSVHelper.ResponseMessage;
import com.example.demo.Utils.DTOs.DepartmentAddEditDTO;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Department;
import com.example.demo.model.User;
import com.example.demo.service.DepartmentService;

@RestController
@RequestMapping("/api/department")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

//	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/get")
	public List<DepartmentAddEditDTO> getAllDepartment() throws  InterruptedException{
		return departmentService.getAllDepartment();
	}
	@GetMapping("/get-paginated and search")
	public ResponseEntity<Page<Department>> getDepartmentsWithSearchAndPagination(DepartmentPage departmentPage, DepartmentSearchCriteria departmentSearchCriteria){
		return new ResponseEntity<>(departmentService.getDepartments(departmentPage,departmentSearchCriteria), HttpStatus.OK);
	}


	@GetMapping("/all-for-drop-down")
	public List<GetAllForDropDownDTO> getAllDepartmentForDropDown(){
		return  departmentService.getAllForDropDown();
	}

	@GetMapping("/get-paginated")
	public List<DepartmentAddEditDTO> getDepartmentsWithPagination(
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy){
		return departmentService.getDepartmentsWithPagination(pageNo, pageSize, sortBy);
	}
	
	@GetMapping("/{id}/users")
	public ResponseEntity<List<User>> getUsersOfDepartment(@PathVariable("id") Integer id) throws ResourceNotFoundException{
		return departmentService.getAllUsersOfDepartment(id);
	}

	@GetMapping("/by-id")
	public ResponseEntity<Department> getDepartmentById(@RequestParam("id") Integer id) throws ResourceNotFoundException {
		return departmentService.getDepartmentById(id);
	}
	
//	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/add")
	public Department addDepartment(@RequestBody DepartmentAddEditDTO departmentAddEditDTO)throws ResourceNotFoundException {
		return departmentService.addDepartment(departmentAddEditDTO);
	}
	
//	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/update")
	public Department updateDepartment(@RequestBody DepartmentAddEditDTO departmentAddEditDTO) throws ResourceNotFoundException {
		return departmentService.updateDepartment(departmentAddEditDTO);
	}
		
//	@CrossOrigin(origins = "http://localhost:4200")
	 @DeleteMapping("/delete")
	  void deleteEmployee(@RequestParam("id") Integer id) throws ResourceNotFoundException {
	    departmentService.deleteDepartment(id);
	 }	
	
//	@CrossOrigin(origins = "http://localhost:4200")
 	@PostMapping("/extract-departments")
	public void extractCsv(HttpServletResponse response) throws InterruptedException, IOException {
		List<DepartmentAddEditDTO> departments = departmentService.getAllDepartment();
		response.setContentType("text/csv");
				String headerValue = "attachment; filename=departments" + ".csv";
				ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
				String[] csvHeader = {"User ID", "Name", "Color", "Code", "Hod Name"};
				String[] nameMapping = {"id", "name", "color", "code", "hodName"};
				csvWriter.writeHeader(csvHeader);
				for (DepartmentAddEditDTO department : departments) {
					csvWriter.write(department, nameMapping);
					}
				csvWriter.close();
	}
	
	 @PostMapping("/upload")
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";
	    if (CSVHelper.hasCSVFormat(file)) {
	      try {
	        departmentService.save(file);
	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	      } catch (Exception e) {
	        message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	      }
	    }
	    message = "Please upload a csv file!";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	  }
	

}
