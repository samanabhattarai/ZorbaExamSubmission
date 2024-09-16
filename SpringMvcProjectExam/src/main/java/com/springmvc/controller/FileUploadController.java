package com.springmvc.controller;

import com.springmvc.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadController {


    @Autowired
    private FileUploadService fileUploadService;


    @RequestMapping(value = "/uploadInventory", method = RequestMethod.GET)
    public String showUploadPage () {
        return "uploadExcel";
    }

    @RequestMapping(value = "/uploadInventory", method = RequestMethod.POST)
    public String uploadExcelFile (@RequestParam("file") MultipartFile file, Model model) {
        if (file.isEmpty ()) {
            model.addAttribute ("message", "Please select a file to upload");
            return "uploadExcel";
        }

        try {
            fileUploadService.save (file);
            model.addAttribute ("message", "File uploaded and processed successfully");
        } catch (Exception e) {
            model.addAttribute ("message", "An error occurred: " + e.getMessage ());
        }
        return "uploadExcel";


    }

}
