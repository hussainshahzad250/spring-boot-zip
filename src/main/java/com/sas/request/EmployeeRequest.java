package com.sas.request;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmployeeRequest {

    private int start;
    private int limit;

    private Long employeeId;
    private String startDate;
    private String endDate;
}
