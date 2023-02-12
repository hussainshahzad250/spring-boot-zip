package com.sas.controller;

import com.sas.request.EmployeeRequest;
import com.sas.service.DownloadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class DownloadController {

    @Autowired
    private DownloadService downloadService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/downloadZipFile")
    public void downloadZipFile(HttpServletResponse response) {
        downloadService.downloadZipFile(response, getListOfFileNames());
    }

    private List<String> getListOfFileNames() {
        List<String> listOfFileNames = new ArrayList<>();
        listOfFileNames.add("D:\\QR-Code\\1.jpg");
        listOfFileNames.add("D:\\QR-Code\\2.jpg");
        listOfFileNames.add("D:\\QR-Code\\3.jpg");
        return listOfFileNames;
    }

}