package com.springmvc.controller;

import com.springmvc.entity.User;
import com.springmvc.model.UserModel;
import com.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public String saveStudent(@ModelAttribute UserModel userModel, Model model) {
        String response = userService.saveUserData(userModel);
        model.addAttribute("message", response);
        return "success";
    }

    @GetMapping("/getAllUsers")
    public String getAllUser(Model model) {
        List<UserModel> userModels = this.userService.getUsers();
        model.addAttribute("allUserInfo",userModels);
        return "userDashboard";
    }


    @GetMapping("/getUserById/{id}")
    public ModelAndView getUserById(@PathVariable("id") String userId) {
        Integer usersId = Integer.parseInt(userId);
        ModelAndView modelAndView = new ModelAndView("viewUser");
        UserModel userModel = this.userService.getUserById(usersId);
        modelAndView.addObject("userObj", userModel);
        return modelAndView;
    }


}
