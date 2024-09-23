package com.springmvc.controller;

import com.springmvc.dao.UserLoginAuditDAO;
import com.springmvc.entity.LoginRequest;
import com.springmvc.entity.LogoutRequest;
import com.springmvc.entity.UserLoginAudit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

@Controller
@Slf4j
public class AuthController {

    @Autowired
    private UserLoginAuditDAO userLoginAuditDAO;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {

        if (StringUtils.isEmpty (loginRequest.getUsername ())
                || StringUtils.isEmpty (loginRequest.getPassword ()))

        {

            return "username and password not valid";
        }

        // If successful, log the login
        UserLoginAudit audit = new UserLoginAudit();
        audit.setUserId(23455666);
        audit.setUserName(loginRequest.getUsername());
        audit.setUserType("Admin");
        audit.setLoginTime(LocalDateTime.now());
        userLoginAuditDAO.save(audit);

        return "Login successful";
    }

    @PostMapping("/logout")
    public String logout(@RequestBody LogoutRequest logoutRequest) {

        UserLoginAudit audit = userLoginAuditDAO.findById(logoutRequest.getUserId());
        if (audit != null) {
            audit.setLogoutTime(LocalDateTime.now());
            userLoginAuditDAO.update(audit);
        }
        return "Logout successful";
    }
}

