package com.sas.response;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;

@Data
@ToString
public class EmployeeResponse {
    private Long employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private String gender;

    private String mobile;

    private String dob;
}
