package com.springmvc.controller;

import com.springmvc.dao.UserDao;
import com.springmvc.model.CategoryInventoryModel;
import com.springmvc.model.CategoryModel;
import com.springmvc.model.CustomerModel;
import com.springmvc.model.UserModel;
import com.springmvc.service.InventoryService;
import com.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


@Controller
public class CustomerController {

    private final UserDao userDao;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private UserService userService;

    public CustomerController (UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping(value = "/customer/login")
    public String login () {
        return "customerLogin";
    }

    @PostMapping(value = "/customer/login")
    public String login (@ModelAttribute("customer") CustomerModel customerModel, Model model) {
        if (StringUtils.isEmpty (customerModel.getUserName ())
                || StringUtils.isEmpty (customerModel.getPassword ())
                || StringUtils.isEmpty (customerModel.getRoleName ()))
        {
            model.addAttribute ("message", "<h5>Please provide proper username and password!</h5>");
            return "customerLogin";
        }
        UserModel user = userDao.getUserByUserNameAndPassword (customerModel.getUserName (), customerModel.getPassword ());

        if (user != null && user.getRoles().stream ().anyMatch (role -> role.getRoleName ().equals (customerModel.getRoleName()))) {
            return getCustomerLandingPage (model, user, 0);
        }
        model.addAttribute ("message", "<h5>Provided wrong login credentials!</h5>");
        return "customerLogin";

    }

    private String getCustomerLandingPage (Model model, UserModel user, int categoryId) {
        model.addAttribute ("user", user);
        StringBuilder categoryList = new StringBuilder ();
        for(CategoryModel categoryModel: inventoryService.getAllCategories()) {
            categoryList.append ("<option value=").append (categoryModel.getCategoryId ());
            if(categoryId == categoryModel.getCategoryId()) {
                categoryList.append (" selected");
            }
            categoryList.append (">").append (categoryModel.getCategoryName ()).append ("</option>");
        }
        model.addAttribute ("categoryList", categoryList.toString ());
        if(categoryId > 0) {
            model.addAttribute("inventoryList", inventoryService.getInventoryByCategory(categoryId));
        }
        return "customerDashboard";
    }

    @PostMapping("/customer/{customerId}/addToCart")
    public String filterInventory(@PathVariable(name="customerId") int customerId, @ModelAttribute (name="addToCart") CategoryInventoryModel categoryInventoryModel, Model model) {
        UserModel user = userDao.getUserById(customerId);
        System.out.println ("Data from customer dashboard page: " + categoryInventoryModel);

        if(categoryInventoryModel.getInventoryId() > 0) {
            // save to cart and redirect to landing page
            model.addAttribute ("message", userService.saveCart(customerId, categoryInventoryModel.getInventoryId()));
            return getCustomerLandingPage (model, user, 0);
        }else{
            return getCustomerLandingPage (model, user, categoryInventoryModel.getCategoryId());
        }

    }

}
