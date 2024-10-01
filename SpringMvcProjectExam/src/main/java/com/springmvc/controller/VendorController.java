package com.springmvc.controller;

import com.springmvc.dao.UserDao;

import com.springmvc.model.InventoryModel;
import com.springmvc.model.UserModel;
import com.springmvc.model.UserLoginModel;
import com.springmvc.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class VendorController {

    @Autowired
    InventoryService inventoryService;


    @PreAuthorize("hasAnyRole('ROLE_VENDOR','ROLE_ADMIN')")
    @GetMapping(value = "/vendors")
    public String login (Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel user = (UserModel) auth.getPrincipal();
        model.addAttribute ("message", "<h5>Welcome " + user.getName () + "!</h5>");
        return "addInventory";
    }

    @PreAuthorize("hasAnyRole('ROLE_VENDOR','ROLE_ADMIN')")
    @PostMapping("/inventory")
    public String saveInventory (@ModelAttribute("InventoryModel") InventoryModel inventoryModel, Model model) {
        System.out.println ("Data from page " + inventoryModel);
        if (inventoryModel.getName() == null || inventoryModel.getName().isEmpty()) {
            model.addAttribute("message", "Name is required");
            return "addInventory";
        }
        if (inventoryModel.getQuantity() <= 0) {
            model.addAttribute("message", "Quantity must be greater than zero");
            return "addInventory";
        }
        if (inventoryModel.getPrice() <= 0) {
            model.addAttribute("message", "Price must be greater than zero");
            return "addInventory";
        }
        if (inventoryModel.getImage() == null || inventoryModel.getImage ().isEmpty ()) {
            model.addAttribute("message", "Image is required");
            return "addInventory";
        }
        if (inventoryModel.getDescription() == null || inventoryModel.getDescription().isEmpty()) {
            model.addAttribute("message", "Description is required");
            return "addInventory";
        }

        //Save inventory to database
        String response = inventoryService.saveInventory(inventoryModel);
        model.addAttribute("message", response);
        return "addInventory";
    }

    @PreAuthorize("hasAnyRole('ROLE_VENDOR','ROLE_ADMIN')")
    @GetMapping("/inventory")
    public String getAllInventory (Model model) {
        List<InventoryModel> inventoryList = inventoryService.getAllInventory();
        model.addAttribute("inventoryList", inventoryList);
        return "viewInventory";
    }

}
