package com.springmvc.security;

import com.springmvc.model.RoleModel;
import com.springmvc.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(javax.servlet.http.HttpServletRequest httpServletRequest,
                                        javax.servlet.http.HttpServletResponse httpServletResponse,
                                        org.springframework.security.core.Authentication authentication) {
        HttpSession session = httpServletRequest.getSession();
        UserModel authUser = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("username", authUser.getUsername());
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        String roleName = httpServletRequest.getParameter ("roleName");
        String roleNameFromDatabase = "";
        for(RoleModel role : authUser.getRoles()) {
            if(role.getRoleName().equals(roleName)) {
                roleNameFromDatabase = role.getRoleName();
                break;
            }
        }
        log.info ("RoleName from request is {} and from database is [{}]", roleName, roleNameFromDatabase);
        switch (roleNameFromDatabase){
            case "ADMIN":
                log.debug ("User {} has role {}", authUser.getUsername(), roleNameFromDatabase);
                session.setAttribute("role", "ADMIN");
                setDestination(httpServletResponse,   "admin");
                break;
            case "CUSTOMER":
                log.debug ("User {} has role {}", authUser.getUsername(), roleNameFromDatabase);
                session.setAttribute("role", "CUSTOMER");
                setDestination(httpServletResponse,  "users");
                break;
            case "VENDOR":
                log.debug ("User {} has role {}", authUser.getUsername(), roleNameFromDatabase);
                session.setAttribute("role", "VENDOR");
                setDestination(httpServletResponse,  "vendors");
                break;
            default:
                log.debug("User {} has  {} role assigned!", authUser.getUsername(), roleNameFromDatabase);
                setDestination(httpServletResponse,  "login?error=true");
                break;
        }

    }

    private void setDestination(HttpServletResponse httpServletResponse, String destination) {
        try
        {
            httpServletResponse.sendRedirect(destination);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
