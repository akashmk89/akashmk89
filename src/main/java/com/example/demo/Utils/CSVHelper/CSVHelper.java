package com.example.demo.Utils.CSVHelper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.repository.UserRepository;
import com.example.demo.model.Department;


public class CSVHelper {

	@Autowired
	public UserRepository userDAO;
	
  public static String TYPE = "text/csv";
  static String[] HEADERs = { "Id", "Title", "Description", "Published" };

  public static boolean hasCSVFormat(MultipartFile file) {

    if (!TYPE.equals(file.getContentType())) {
      return false;
    }

    return true;
  }

  public static List<Department> csvToDepartments(InputStream is) {
    try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        CSVParser csvParser = new CSVParser(fileReader,
            CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

      List<Department> departments = new ArrayList<Department>();

      Iterable<CSVRecord> csvRecords = csvParser.getRecords();

      for (CSVRecord csvRecord : csvRecords) {
//    	  User hod = userDAO.findById(csvRecord.get("hodId")).orElseThrow(() -> new ResourceNotFoundException("User with ID Not Found" + departmentAddEditDTO.getHodId()));
    	  
    	Department department = Department.builder()
    					.code(csvRecord.get("name"))
    					.color(csvRecord.get("color"))
    					.name(csvRecord.get("code"))
    					.build();
//        Department department = new Department(
//        		csvRecord.get("name"),
//        		csvRecord.get("color"),
//        		csvRecord.get("code"),
//        		csvRecord.get("hodId"));
//              Long.parseLong(csvRecord.get("Id")),
//              csvRecord.get("Title"),
//              csvRecord.get("Description"),
//              Boolean.parseBoolean(csvRecord.get("Published"))
//            );

        departments.add(department);
      }

      return departments;
    } catch (IOException e) {
      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
    }
  }


}
