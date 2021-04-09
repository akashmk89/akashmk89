package com.example.demo.repository;

import com.example.demo.Utils.SearchCriteria.DepartmentPage;
import com.example.demo.Utils.SearchCriteria.DepartmentSearchCriteria;
import com.example.demo.model.Department;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
@Repository
public class DepartmentCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public DepartmentCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Department> findAllWithFilters(DepartmentPage departmentPage, DepartmentSearchCriteria departmentSearchCriteria) {
        CriteriaQuery<Department> criteriaQuery = criteriaBuilder.createQuery(Department.class);
        Root<Department> departmentRoot = criteriaQuery.from(Department.class);
        Predicate predicate = getPredicate(departmentSearchCriteria, departmentRoot);
        criteriaQuery.where(predicate);
        setOrder(departmentPage, criteriaQuery, departmentRoot);
        TypedQuery<Department> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(departmentPage.getPageNumber() * departmentPage.getPageSize());
        typedQuery.setMaxResults(departmentPage.getPageSize());
        Pageable pageable = getPageable(departmentPage);
        long departmentsCount = getDepartmentsCount(predicate);
        return new PageImpl<>(typedQuery.getResultList(), pageable, departmentsCount);
    }


    private Predicate getPredicate(DepartmentSearchCriteria departmentSearchCriteria, Root<Department> departmentRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if (Objects.nonNull(departmentSearchCriteria.getName())) {
            predicates.add(
                    criteriaBuilder.like(departmentRoot.get("name"),
                            "%" + departmentSearchCriteria.getName() + "%")
            );
        }
        if (Objects.nonNull(departmentSearchCriteria.getColor())) {
            predicates.add(
                    criteriaBuilder.like(departmentRoot.get("color"),
                            "%" + departmentSearchCriteria.getColor() + "%")
            );
        }
        return  criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void setOrder(DepartmentPage departmentPage, CriteriaQuery<Department> criteriaQuery, Root<Department> departmentRoot){
    if(departmentPage.getSortDirection().equals(Sort.Direction.ASC)){
        criteriaQuery.orderBy(criteriaBuilder.asc(departmentRoot.get(departmentPage.getSortBy())));
        }else {
        criteriaQuery.orderBy(criteriaBuilder.desc(departmentRoot.get(departmentPage.getSortBy())));
    }
    }

    private  Pageable getPageable(DepartmentPage departmentPage){
        Sort sort = Sort.by(departmentPage.getSortDirection(), departmentPage.getSortBy());
        return PageRequest.of(departmentPage.getPageNumber(), departmentPage.getPageSize(), sort);
    }

    private  long getDepartmentsCount(Predicate predicate){
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Department> countRoot = countQuery.from((Department.class));
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return  entityManager.createQuery(countQuery).getSingleResult();
    }
}


