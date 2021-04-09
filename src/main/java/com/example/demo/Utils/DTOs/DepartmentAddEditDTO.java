package com.example.demo.Utils.DTOs;

import java.util.List;

import com.example.demo.model.User;

public class DepartmentAddEditDTO {
	int id;
	public int hodId;
	public String name;
	public String color;
	public String code;
	public String hodName;
	public List<User> departmentMembers;
	
	
	public List<User> getDepartmentMembers() {
		return departmentMembers;
	}
	public void setDepartmentMembers(List<User> departmentMembers) {
		this.departmentMembers = departmentMembers;
	}
	public String getHodName() {
		return hodName;
	}
	public void setHodName(String hodName) {
		this.hodName = hodName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHodId() {
		return hodId;
	}
	public void setHodId(int hodId) {
		this.hodId = hodId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
		
}
