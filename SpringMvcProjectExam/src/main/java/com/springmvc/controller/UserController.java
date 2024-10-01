package com.springmvc.controller;
import com.springmvc.model.AddRole;
import com.springmvc.model.RoleModel;
import com.springmvc.model.UserModel;
import com.springmvc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class UserController {

    public static final String VIEW_USERS = "viewUsers";
    public static final String REGISTER_USER = "registerUser";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/users/register")
    public String saveUser (@ModelAttribute("userModel") UserModel userModel, Model model) {
        log.info ("Registering a new user {}", userModel);

        if (userModel.getEmail () == null || !userModel.getEmail ().contains ("@")) {
            model.addAttribute ("message", "Invalid email format!");
            return REGISTER_USER;
        }

        if (userModel.getMobile () == null || userModel.getMobile ().length () < 10) {
            model.addAttribute ("message", "Mobile number should be exactly 10 digits!");
            return REGISTER_USER;
        }
        if (userModel.getPassword ().length () < 8) {
            model.addAttribute ("message", "Password should be at least 8 characters!");
            return REGISTER_USER;
        }
        String response = userService.saveUserData(userModel);
        model.addAttribute ("message", response);
        log.info ("Registered a new user {}", response);
        return getAllUser(model);
    }

    @GetMapping("/users")
    public String getAllUser (Model model) {
        log.info ("Getting all users...");
        List<UserModel> userModels = new ArrayList<> (this.userService.getUsers());
        model.addAttribute ("users", userModels);
        return VIEW_USERS;
    }


    @GetMapping("/users/user/{userid}")
    public String getUserById (@PathVariable("userid") int userId, Model model) {
        UserModel userModel = this.userService.getUserById (userId);
        List<UserModel> userModels = new ArrayList<> ();
        userModels.add (userModel);
        model.addAttribute ("users", userModels);
        return VIEW_USERS;
    }

    @GetMapping("/users/register")
    public String registerUser (@ModelAttribute UserModel userModel) {
        return REGISTER_USER;
    }


    @GetMapping("/users/roles/{userId}")
    public String viewByRole(@PathVariable ("userId") int userId, Model model) {
        UserModel user = userService.getUserById(userId);
        model.addAttribute ("user", user);
        return "addRole";
    }

    @GetMapping("/users/roles/delete/{userId}")
    public String viewDeleteRolesPage(@PathVariable ("userId") int userId, Model model) {
        UserModel user = userService.getUserById(userId);
        model.addAttribute ("user", user);
        model.addAttribute ("roles", user.getRoles().stream().map (role -> role.getRoleName ()).collect(Collectors.toList ()));
        return "removeRole";
    }


    @PostMapping("/users/roles/{userId}")
    public String saveRoles (@PathVariable ("userId") int userId, @ModelAttribute AddRole roleModel, Model model) {
        if(roleModel.getRoleNames () == null || roleModel.getRoleNames().length ==0){
            model.addAttribute ("message", "<h5>Must select at least one role!</h5>");
            return  viewByRole(userId, model);
        }
        // update user with roles data
        String message = userService.updateUserRole(userId, roleModel.getRoleNames());
        model.addAttribute ("message", "<h5>"+message+"</h5>");
        return  viewByRole(userId, model);
    }

    @PostMapping("/users/roles/delete/{userId}")
    public String removeRoles(@PathVariable("userId") int userId, @ModelAttribute AddRole removeRole, Model model) {
        if(removeRole.getRoleNames () == null || removeRole.getRoleNames().length ==0){
            model.addAttribute ("message", "<h5>Must select at least one role to remove!</h5>");
            return  viewDeleteRolesPage(userId, model);
        }
        String message = userService.removeRolesFromUser(userId, removeRole.getRoleNames());
        model.addAttribute ("message", message);
        return  viewDeleteRolesPage(userId, model);
    }

    @GetMapping("/users/download")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        List<UserModel> users = userService.getUsers();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Users");

        // Create header row
        HSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("User ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Email");
        header.createCell(3).setCellValue("Mobile");
        header.createCell(4).setCellValue("UserName");
        header.createCell(5).setCellValue("Password");
        header.createCell(6).setCellValue("Roles");



        // Create data rows
        int rowNum = 1;
        for (UserModel userModel : users) {
            HSSFRow row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(userModel.getUserId ());
            row.createCell(1).setCellValue(userModel.getName ());
            row.createCell(2).setCellValue(userModel.getEmail ());
            row.createCell(3).setCellValue(userModel.getMobile ());
            row.createCell(4).setCellValue(userModel.getUsername ());
            row.createCell(5).setCellValue(userModel.getPassword ());
            row.createCell(6).setCellValue(userModel.getRoles().stream ().map (RoleModel::getRoleName).collect(Collectors.joining (",")));
        }

        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment; filename=users.xls");
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
