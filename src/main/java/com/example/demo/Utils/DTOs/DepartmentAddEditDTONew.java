package com.example.demo.Utils.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class DepartmentAddEditDTONew {
    int id;
    public int hodId;
    public String name;
    public String color;
    public String code;
    public String hodName;
    public int [] departmentMembers;
}
