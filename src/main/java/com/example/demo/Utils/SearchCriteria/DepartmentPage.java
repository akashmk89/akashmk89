package com.example.demo.Utils.SearchCriteria;

import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QSort;
@Data
public class DepartmentPage {
    private int pageNumber = 0;
    private int PageSize = 10;
    private Sort.Direction sortDirection = Sort.Direction.ASC;
    private String sortBy = "name";
}
