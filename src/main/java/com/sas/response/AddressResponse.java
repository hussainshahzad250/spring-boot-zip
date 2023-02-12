package com.sas.response;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AddressResponse {
    private int id;

    private String addressLine;

    private String city;

    private String state;

    private String pincode;

    private Long employeeId;
}
