package com.bridgelabz.bookstoresbproject.service;

import com.bridgelabz.bookstoresbproject.dto.LoginDTO;
import com.bridgelabz.bookstoresbproject.dto.UserDTO;
import com.bridgelabz.bookstoresbproject.exception.BookStoreException;
import com.bridgelabz.bookstoresbproject.model.UserModel;
import com.bridgelabz.bookstoresbproject.repository.UserRepository;
import com.bridgelabz.bookstoresbproject.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    List<UserModel> userList = new ArrayList<>();
    //create dependency  injection for UserRepository class
    @Autowired
    UserRepository repoUser;
    //create dependency  injection for TokenUtil class
    @Autowired
    TokenUtil tokenUtil;

    @Override
    public String welcomeMsg() {
        return "Welcome to the Book Store Project ";

    }
    //saving a specific record by using the method save() of CrudRepository
    @Override
    public UserModel userRegistration(UserDTO userDTO) {
        if(userDTO.password.equals(userDTO.confirmPassword)){
            UserModel userModelObj = new UserModel(userDTO);
            return repoUser.save(userModelObj);
        } else
            throw new BookStoreException("password and confirm password not match");
    }
    //getting all the record by using the method findAll() of CrudRepository
    @Override
    public List<UserModel> getAll() {
        userList = repoUser.findAll();
        return userList;
    }
    //getting a specific record by using the method findById() of CrudRepository
    @Override
    public UserModel getUserDataByID(long id) {
        Optional<UserModel> userModelObj = repoUser.findById(id);
        if (userModelObj.isPresent())
            return userModelObj.get();
        else
            throw new BookStoreException("Id is not present in database");
    }
    @Override
    public UserModel getByToken(String token) {
        long userToken = tokenUtil.decodeToken(token);
        Optional<UserModel> userModelObj = repoUser.findById(userToken);
        if (userModelObj.isPresent())
            return repoUser.findById(userToken).get();
        else
            throw new BookStoreException("Token is not match");
    }

    @Override
    public UserModel getUserDataByEmail(String email) {
        UserModel userDataList = repoUser.findDataByMail(email);
        if (userDataList != null)
            throw new BookStoreException("Sorry!!! we can not find the user email:" + email);
        else
            return userDataList;
    }
    //updating an existing record by using the method save() of CrudRepository
    @Override
    public UserModel updateUserByID(UserDTO userDTO, long id) {
        Optional<UserModel> checkID = repoUser.findById(id);
        if (checkID.isPresent()) {
            UserModel userModel = new UserModel(userDTO);
            userModel.setUserID(id);
            UserModel userModelUpdate = repoUser.save(userModel);
            return userModelUpdate;
        } else
            throw new BookStoreException("Id is not found ,updation fail");

    }
    //updating an existing record by using the method save() of CrudRepository
    @Override
    public UserModel updateUserByEmail(UserDTO userDTO, String email) {
        UserModel checkMail = repoUser.findDataByMail(email);
        Long id = checkMail.getUserID();
        if (repoUser.findDataByMail(email) != null) {
            UserModel userModel = new UserModel(userDTO);
            userModel.setEmail(email);
            userModel.setUserID(id);
            UserModel userModelUpdate = repoUser.save(userModel);
            return userModelUpdate;
        } else
            throw new BookStoreException("email is not found ,updation fail");
    }
    @Override
    public UserModel updateUserByToken(UserDTO userDTO, String token) {
        long userToken = tokenUtil.decodeToken(token);
        Optional<UserModel> userModelObj = repoUser.findById(userToken);
        if (userModelObj.isPresent()) {
            UserModel userModel = new UserModel(userDTO);
            userModel.setUserID(userModelObj.get().getUserID());
            UserModel userModelUpdate = repoUser.save(userModel);
            return userModelUpdate;
        } else
            throw new BookStoreException("Id is not found ,updation fail");
    }


    @Override
    public String loginUser(LoginDTO loginDTO) {
        UserModel checkLoginData = repoUser.checkLoginData(loginDTO.email, loginDTO.password);
        if (checkLoginData != null)
            return "Congratulation !! You have logged in successfully..";
        else
            throw new BookStoreException("Email or password is incorrect,login fail");
    }

    @Override
    public UserModel changePassword(String email, String newPassword, String confirmPassword) {
        UserModel fetchUserData = repoUser.changeUserPassword(newPassword, confirmPassword, email);
        return repoUser.save(fetchUserData);
    }
    // create a forget or change password method
    @Override
    public UserModel forgetPassword(UserDTO userDTO, String email) {
        UserModel user = repoUser.findDataByMail(email);
        if (user != null) {
            user.setPassword(userDTO.getPassword());
            user.setConfirmPassword(userDTO.getConfirmPassword());
            repoUser.save(user);
            return user;
        } else
            throw new BookStoreException("Sorry !! we can not find the user email:");
    }

    @Override
    public String deleteUserData(long id) {
        Optional<UserModel> checkID = repoUser.findById(id);
        if (checkID.isPresent())
            repoUser.deleteById(id);
        else
            throw new BookStoreException("Id is not found");
        return "data deleted successfully " +checkID;
    }


}
