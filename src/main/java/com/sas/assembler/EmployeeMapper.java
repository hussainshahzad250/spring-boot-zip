package com.sas.assembler;

import com.sas.entity.Employee;
import com.sas.response.EmployeeResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class EmployeeMapper {

    public Employee dtoToEntity(EmployeeResponse request) {
        Employee employee = new Employee();
        employee.setEmployeeId(request.getEmployeeId());
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setEmail(request.getEmail());
        employee.setGender(request.getGender());
        employee.setMobile(request.getMobile());
        employee.setDob(request.getDob());
        return employee;
    }

    public EmployeeResponse entityToDto(Employee request) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployeeId(request.getEmployeeId());
        employeeResponse.setFirstName(request.getFirstName());
        employeeResponse.setLastName(request.getLastName());
        employeeResponse.setEmail(request.getEmail());
        employeeResponse.setGender(request.getGender());
        employeeResponse.setMobile(request.getMobile());
        employeeResponse.setDob(request.getDob());
        return employeeResponse;
    }

    public List<EmployeeResponse> asResponseList(List<Employee> responses) {
        if (CollectionUtils.isEmpty(responses))
            return Collections.emptyList();
        return responses.parallelStream().map(this::entityToDto).collect(Collectors.toList());
    }

    public Object constructEmployeeDetail(List<Employee> employeeDetails) {
        if (!CollectionUtils.isEmpty(employeeDetails)) {
            return employeeDetails.stream().map(this::constructEmployeeData).collect(Collectors.toList());
        }
        return null;
    }

    private List<String> constructEmployeeData(Employee request) {
        List<String> kycData = new ArrayList<>();
        kycData.add(request.getEmployeeId() != null ? String.valueOf(request.getEmployeeId()) : "");
        kycData.add(request.getFirstName() != null ? request.getFirstName() : "");
        kycData.add(request.getLastName() != null ? request.getLastName() : "");
        kycData.add(request.getEmail() != null ? request.getEmail() : "");
        return kycData;
    }
}