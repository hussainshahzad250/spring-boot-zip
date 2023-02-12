package com.sas.assembler;

import com.sas.entity.Address;
import com.sas.response.AddressResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapper {

    public Address dtoToEntity(AddressResponse request) {
        Address address = new Address();
        address.setId(request.getId());
        address.setAddressLine(request.getAddressLine());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setPincode(request.getPincode());
        address.setEmployeeId(request.getEmployeeId());
        return address;
    }

    public AddressResponse entityToDto(Address request) {
        AddressResponse address = new AddressResponse();
        address.setId(request.getId());
        address.setAddressLine(request.getAddressLine());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setPincode(request.getPincode());
        address.setEmployeeId(request.getEmployeeId());
        return address;
    }

    public List<AddressResponse> asResponseList(List<Address> responses) {
        if (CollectionUtils.isEmpty(responses))
            return Collections.emptyList();
        return responses.parallelStream().map(this::entityToDto).collect(Collectors.toList());
    }

    public List<List<String>> constructAddressDetailsData(List<Address> addressDetails) {
        if (!CollectionUtils.isEmpty(addressDetails)) {
            return addressDetails.stream().map(this::constructAddressDetail).collect(Collectors.toList());
        }
        return null;
    }

    private List<String> constructAddressDetail(Address address) {
        List<String> repayment = new ArrayList<>();
        repayment.add(StringUtils.hasText(String.valueOf(address.getAddressLine())) ? String.valueOf(address.getAddressLine()) : "");
        repayment.add(StringUtils.hasText(String.valueOf(address.getCity())) ? String.valueOf(address.getCity()) : "");
        repayment.add(StringUtils.hasText(String.valueOf(address.getState())) ? String.valueOf(address.getState()) : "");
        repayment.add(StringUtils.hasText(String.valueOf(address.getPincode())) ? String.valueOf(address.getPincode()) : "");
        return repayment;
    }
}