package com.sas.service;

import com.sas.assembler.AddressMapper;
import com.sas.assembler.EmployeeMapper;
import com.sas.dao.AddressDao;
import com.sas.dao.EmployeeDao;
import com.sas.entity.Address;
import com.sas.entity.Employee;
import com.sas.request.EmployeeRequest;
import com.sas.utils.ExcelGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Slf4j
@Service
public class DownloadService {

    private static final String EMPLOYEE_DETAILS_HEADER = "EMP_ID,FIRST NAME,LAST NAME,EMAIL";
    private static final String ADDRESS_DETAILS_HEADER = "ADDRESS LINE,CITY,STATE,PINCODE";
    @Autowired
    private AddressDao addressDao;

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private ExcelGeneratorUtil excelGeneratorUtil;


    public void downloadZipFile(HttpServletResponse response, List<String> listOfFileNames) {
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment; filename=download.zip");
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream())) {
            for (String fileName : listOfFileNames) {
                FileSystemResource fileSystemResource = new FileSystemResource(fileName);
                ZipEntry zipEntry = new ZipEntry(fileSystemResource.getFilename());
                zipEntry.setSize(fileSystemResource.contentLength());
                zipEntry.setTime(System.currentTimeMillis());

                zipOutputStream.putNextEntry(zipEntry);

                StreamUtils.copy(fileSystemResource.getInputStream(), zipOutputStream);
                zipOutputStream.closeEntry();
            }
            zipOutputStream.finish();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void downloadEmployeeDetails(EmployeeRequest request, HttpServletResponse httpServletResponse) {
        List<Employee> employeeDetails = employeeDao.getEmployeeDetails(request);
        List<Address> addressDetails = addressDao.getAddressDetails(request);

        List<Map<String, Object>> maps = new ArrayList<>();
        //Creating Employee Detail excel
        Map<String, Object> loanMap = excelGeneratorUtil.populateHeaderAndName(EMPLOYEE_DETAILS_HEADER, "employee_detail.xlsx");
        loanMap.put("RESULTS", employeeMapper.constructEmployeeDetail(employeeDetails));
        maps.add(loanMap);

        //Creating Address detail excel
        Map<String, Object> repaymentMap = excelGeneratorUtil.populateHeaderAndName(ADDRESS_DETAILS_HEADER, "address_detail.xlsx");
        repaymentMap.put("RESULTS", addressMapper.constructAddressDetailsData(addressDetails));
        maps.add(repaymentMap);

        //Download as zip folder
        excelGeneratorUtil.downloadMultipleZipDocument(httpServletResponse, maps);
    }
}
