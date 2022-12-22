package com.bridgelabz.bookstoresbproject.service;

import com.bridgelabz.bookstoresbproject.dto.LoginDTO;
import com.bridgelabz.bookstoresbproject.dto.UserDTO;
import com.bridgelabz.bookstoresbproject.model.UserModel;

import java.util.List;

public interface IUserService {
    String welcomeMsg();
    public UserModel userRegistration(UserDTO userDTO);
    public List<UserModel> getAll();
    public UserModel getUserDataByID(long id);
    public UserModel getUserDataByEmail(String email);
    public UserModel updateUserByID(UserDTO userDTO, long id);
    public UserModel updateUserByEmail(UserDTO userDTO,String email);
    public UserModel updateUserByToken(UserDTO userDTO,String token);
    public String loginUser(LoginDTO loginDTO);
    public  UserModel changePassword(String email,String newPassword,String confirmPassword);
    public  UserModel forgetPassword(UserDTO userDTO,String email);
    public  String deleteUserData(long id);
    public UserModel getByToken(String token);
}
