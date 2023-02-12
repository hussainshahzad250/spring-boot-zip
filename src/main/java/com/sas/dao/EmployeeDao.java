package com.sas.dao;

import com.sas.constant.Constant;
import com.sas.entity.Employee;
import com.sas.request.EmployeeRequest;
import com.sas.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class EmployeeDao implements Constant {

    @PersistenceContext
    private EntityManager entityManager;

    public Long getTotalCount(EmployeeRequest filterRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = criteriaBuilder.createQuery(Long.class);
        Root<Employee> root = criteria.from(Employee.class);
        List<Predicate> predicates = getPredicates(filterRequest, criteriaBuilder, root);
        criteria.select(criteriaBuilder.count(root)).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteria).getSingleResult();
    }

    public List<Employee> getEmployeeDetails(EmployeeRequest filterRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = cb.createQuery(Employee.class);
        Root<Employee> root = criteria.from(Employee.class);
        List<Predicate> predicates = getPredicates(filterRequest, cb, root);
        criteria.select(root).distinct(true).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteria).getResultList();
    }

    private List<Predicate> getPredicates(EmployeeRequest request, CriteriaBuilder cb, Root<Employee> root) {
        List<Predicate> predicates = new ArrayList<>();
        if (request.getEmployeeId() != null) {
            predicates.add(cb.equal(root.get("employeeId"), request.getEmployeeId()));
        }
        if (StringUtils.hasText(request.getStartDate()) && StringUtils.hasText(request.getEndDate())) {
            predicates.add(cb.between(cb.function("", LocalDateTime.class, root.get("createdOn")),
                    DateUtils.stringToDateTime(request.getStartDate(), DD_MM_YYYY),
                    DateUtils.stringToDateTime(request.getEndDate(), DD_MM_YYYY)));
        }
        return predicates;
    }

    public List<Employee> getCollectionDetails(EmployeeRequest filterRequest) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteria = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteria.from(Employee.class);
        List<Predicate> predicates = getPredicates(filterRequest, criteriaBuilder, root);
        criteria.select(root).where(predicates.toArray(new Predicate[0]));
        return entityManager.createQuery(criteria).setFirstResult(filterRequest.getStart())
                .setMaxResults(filterRequest.getLimit()).getResultList();
    }
}
