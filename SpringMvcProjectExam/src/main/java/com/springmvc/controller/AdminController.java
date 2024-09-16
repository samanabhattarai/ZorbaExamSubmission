package com.springmvc.controller;

import com.springmvc.dao.UserDao;
import com.springmvc.model.*;
import com.springmvc.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private InventoryService inventoryService;

    @GetMapping(value = "/admin/login")
    public String login () {
        return "adminLogin";
    }

    @PostMapping(value = "/admin/login")
    public String login (@ModelAttribute("admin") AdminModel adminModel, Model model) {
        if (StringUtils.isEmpty (adminModel.getUserName ())
                || StringUtils.isEmpty (adminModel.getPassword ())
                || StringUtils.isEmpty (adminModel.getRoleName ()))
        {
            model.addAttribute ("message", "<h5>Please provide proper username and password!</h5>");
            return "adminLogin";
        }
        UserModel user = userDao.getUserByUserNameAndPassword (adminModel.getUserName (), adminModel.getPassword ());

        if (user != null && user.getRoles().stream ().anyMatch (role -> role.getRoleName ().equals (adminModel.getRoleName()))) {
            return getDifferentUsers (model, user);
        }
        model.addAttribute ("message", "<h5>Provided wrong login credentials!</h5>");
        return "adminLogin";

    }

    private String getDifferentUsers (Model model, UserModel user) {
        model.addAttribute ("message", "<h5>Welcome " + user.getName () + "!</h5>");
        List<UserModel> users = userDao.getUsers ();
        List<UserModel> vendors = new ArrayList<> ();
        List<UserModel> customers = new ArrayList<> ();
        for(UserModel userModel : users) {
            if(userModel.getRoles().stream ().anyMatch (role -> role.getRoleName ().equals ("VENDOR"))) {
                vendors.add (userModel);
            } else if(userModel.getRoles().stream ().anyMatch (role -> role.getRoleName ().equals ("CUSTOMER"))) {
                customers.add (userModel);
            }
        }
        model.addAttribute ("vendors", vendors);
        model.addAttribute ("customers", customers);
        return "adminDashboard";
    }



}


