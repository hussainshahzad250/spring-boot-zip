package com.sas.controller;

import com.sas.request.EmployeeRequest;
import com.sas.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class ZipController {

    @Autowired
    private DownloadService downloadService;

    @PostMapping("/downloadZip")
    public void createZip(@RequestBody EmployeeRequest request, HttpServletResponse httpServletResponse) {
        downloadService.downloadEmployeeDetails(request,httpServletResponse);
    }
}
