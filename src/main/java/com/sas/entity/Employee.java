package com.sas.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "EMPLOYEE_DETAILS")
public class Employee {

    @Id
    @Column(name = "EMPLOYEE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long employeeId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "DOB")
    private String dob;

    @Column(name = "EMPLOYEE_STATUS")
    private String employeeStatus;

    @Column(name = "EMPLOYMENT_TYPE")
    private String employmentType;

    @Column(name = "CREATED_ON")
    private LocalDateTime createdOn;
}
