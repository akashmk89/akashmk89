package com.example.demo.Utils.DTOs;

import java.util.List;
import java.util.Set;

import com.example.demo.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentAddEditDTO {
	int id;
	public int hodId;
	public String name;
	public String color;
	public String code;
	public String hodName;
	public Set<DepartmentMemberDTO> departmentMembers;
	

		
}
