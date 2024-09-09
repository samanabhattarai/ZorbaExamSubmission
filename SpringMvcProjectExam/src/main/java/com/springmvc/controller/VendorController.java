package com.springmvc.controller;

import com.springmvc.dao.UserDao;

import com.springmvc.model.UserModel;
import com.springmvc.model.VendorModel;
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

    @GetMapping(value = "/vendor/login")
    public String login () {
        return "vendorLogin";
    }

    @PostMapping(value = "/vendor/login")
    public String login (@ModelAttribute("vendor") VendorModel vendorModel, Model model) {
        if (StringUtils.isEmpty (vendorModel.getUserName ()) || StringUtils.isEmpty (vendorModel.getPassword ()) || StringUtils.isEmpty (vendorModel.getRoleName ())) {
            {
                model.addAttribute ("message", "<h5>Please provide proper username and password!</h5>");
                return "vendorLogin";
            }
        }
            UserModel user = userDao.getUserByUserNameAndPassword (vendorModel.getUserName (), vendorModel.getPassword ());

            if (user != null && user.getRoles ().stream ().anyMatch (role -> role.getRoleName ().equals (vendorModel.getRoleName ()))) {
                model.addAttribute ("message", "<h5>Welcome " + user.getName () + "!</h5>");
                return "addInventory";
            }
            model.addAttribute ("message", "<h5>Provided wrong login credentials!</h5>");
            return "vendorLogin";

        }

    }
