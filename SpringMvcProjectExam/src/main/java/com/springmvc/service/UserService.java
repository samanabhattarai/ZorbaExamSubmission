package com.springmvc.service;

import com.springmvc.dao.UserDao;
import com.springmvc.entity.User;
import com.springmvc.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Transactional
    public String saveUserData(UserModel userModel){
        String responseMessage="";
        User user = new User();

        if(userModel.getName() != null && userModel.getEmail() != null && userModel.getMobile() != null
                && userModel.getUserName() != null && userModel.getPassword() != null){

            user.setName(userModel.getName());
            user.setEmail(userModel.getEmail());
            user.setMobile(userModel.getMobile());
            user.setUserName(userModel.getUserName());
            user.setPassword(userModel.getPassword());

            //saved data to database
            responseMessage = this.userDao.saveUser(user);
        }
        else {

            responseMessage = "Could Not Process";
        }

        return responseMessage;
    }

    public UserModel getUserById(Integer userId){

        if(userId == null){
            return new UserModel();
        }
        UserModel userModel = new UserModel();

        User student = this.userDao.getUserById(userId);
        userModel.setName(student.getName());
        userModel.setEmail(student.getEmail());
        userModel.setMobile(student.getMobile());
        userModel.setUserName(student.getUserName());
        userModel.setPassword(student.getPassword());

        return userModel;
    }

    public List<UserModel> getUsers(){

        List<User> users = this.userDao.getUsers();

        List<UserModel> usersModel = new ArrayList<>();

        for(User user : users){

            UserModel userModel  = new UserModel();
            userModel.setUserId(user.getUserId());
            userModel.setName(user.getName());
            userModel.setEmail(user.getEmail());
            userModel.setMobile(user.getMobile());
            userModel.setUserName(user.getUserName());
            userModel.setPassword(user.getPassword());

            usersModel.add(userModel);


        }
        return usersModel;
    }
}
