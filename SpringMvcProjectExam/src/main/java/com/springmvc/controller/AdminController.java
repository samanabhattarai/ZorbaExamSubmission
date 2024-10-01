package com.springmvc.controller;

import com.springmvc.dao.UserDao;
import com.springmvc.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class AdminController {

    @Autowired
    private UserDao userDao;


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/admin")
    public String login (Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserModel authUser = (UserModel) auth.getPrincipal();
        log.info ("Login user: {}", authUser);
        return getDifferentUsers (model, authUser);
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


