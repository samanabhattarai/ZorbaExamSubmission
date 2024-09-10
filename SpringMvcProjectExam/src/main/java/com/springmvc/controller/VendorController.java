package com.springmvc.controller;

import com.springmvc.dao.UserDao;

import com.springmvc.model.InventoryModel;
import com.springmvc.model.UserModel;
import com.springmvc.model.VendorModel;
import com.springmvc.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.*;


@Controller
public class VendorController {
    private final UserDao userDao;

    public VendorController (UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    InventoryService inventoryService;

    @GetMapping(value = "/vendor/login")
    public String login () {
        return "vendorLogin";
    }

    @PostMapping(value = "/vendor/login")
    public String login (@ModelAttribute("vendor") VendorModel vendorModel, Model model) {
        if (StringUtils.isEmpty (vendorModel.getUserName ())
                || StringUtils.isEmpty (vendorModel.getPassword ())
                || StringUtils.isEmpty (vendorModel.getRoleName ()))
        {
                model.addAttribute ("message", "<h5>Please provide proper username and password!</h5>");
                return "vendorLogin";
        }
        UserModel user = userDao.getUserByUserNameAndPassword (vendorModel.getUserName (), vendorModel.getPassword ());

        if (user != null && user.getRoles().stream ().anyMatch (role -> role.getRoleName ().equals (vendorModel.getRoleName()))) {
            model.addAttribute ("message", "<h5>Welcome " + user.getName () + "!</h5>");
            return "addInventory";
        }
        model.addAttribute ("message", "<h5>Provided wrong login credentials!</h5>");
        return "vendorLogin";

    }

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

}
