package com.bridgelabz.bookstoresbproject.controller;

import com.bridgelabz.bookstoresbproject.dto.LoginDTO;
import com.bridgelabz.bookstoresbproject.dto.ResponseBookDTO;
import com.bridgelabz.bookstoresbproject.dto.ResponseDTO;
import com.bridgelabz.bookstoresbproject.dto.UserDTO;
import com.bridgelabz.bookstoresbproject.model.EmailModel;
import com.bridgelabz.bookstoresbproject.model.UserModel;
import com.bridgelabz.bookstoresbproject.service.EmailService;
import com.bridgelabz.bookstoresbproject.service.IUserService;
import com.bridgelabz.bookstoresbproject.util.TokenUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bookStoreSBProject/user")
public class UserController {

    @Autowired
    IUserService serviceUser;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailService serviceEmail;

    List<UserModel> userModelList = new ArrayList<>();
    //Create Api for welcome msg
    @GetMapping("/welcome")
    public String welcomeMsg(){
        return serviceUser.welcomeMsg();
    }
    //Creating a post mapping with token that post the data in the database

//Create Api for registering the new user Record
    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> userRegistrationSend(@Valid @RequestBody UserDTO userDTO){
        UserModel userModel = serviceUser.userRegistration(userDTO);
        String token = tokenUtil.createToken(userModel.getUserID());
        EmailModel emailModel = new EmailModel(userModel.getEmail(),
                "Account sign-up successfully", "user " + userModel + " Token "+token);
        serviceEmail.sendEmail(emailModel);
        ResponseDTO responseDto = new ResponseDTO(" New user registered send in mail",userModel,token);
        ResponseEntity<ResponseDTO> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        log.info(token);
        return response;

    }
    //Create a get mapping Api that retrieves all the data from the database
    @GetMapping("/getAllRecord")
    public ResponseEntity<ResponseDTO> getAll(){
        userModelList = serviceUser.getAll();
        ResponseDTO responseDto = new ResponseDTO("fetch All user data successfully",userModelList,null);
        ResponseEntity<ResponseDTO> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
    //Creating Api for retrieve user Record by ID
    @GetMapping("/getRecordById/{id}")
    public ResponseEntity<ResponseDTO> getUserDatByID(@PathVariable long id){
        UserModel userModel = serviceUser.getUserDataByID(id);
        ResponseDTO responseDTO = new ResponseDTO("fetch user data successfully",userModel,null);
        ResponseEntity<ResponseDTO> response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
        return response;
    }
    //Creating Api for retrieve user Record by Token
    @GetMapping("/getRecordByToken")
    public ResponseEntity<ResponseDTO> getByToken(@RequestParam(value = "token" ,defaultValue = "") String token){
        UserModel userModel = serviceUser.getByToken(token);
        ResponseDTO responseDTO = new ResponseDTO("fetch user data for particular token",userModel,token);
        ResponseEntity<ResponseDTO> response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
        return response;
    }
    //Creating Api for retrieve user Record by Email
    @GetMapping("/getRecordByEmail")
    public ResponseEntity<ResponseBookDTO> getUserDatByEmail(@RequestParam String email){
        UserModel userModel = serviceUser.getUserDataByEmail(email);
        ResponseBookDTO responseDTO =  new ResponseBookDTO("fetch user data successfully by email",userModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseDTO, HttpStatus.OK);
        return response;
    }

    @PutMapping("/updateById/{id}")
    public ResponseEntity<ResponseDTO>updateUserByID(@Valid @RequestBody UserDTO userDTO, @PathVariable long id)  {
        UserModel userModel = serviceUser.updateUserByID(userDTO,id);
        ResponseDTO responseDto = new ResponseDTO("existing user data update successfully", userModel,null);
        ResponseEntity<ResponseDTO> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }
    //creating Api for Updating User details by email
    @PutMapping("/updateByEmail/{email}")
    public ResponseEntity<ResponseBookDTO> updateUserByEmail(@Valid @RequestBody UserDTO userDTO,@PathVariable String email){
        UserModel userModel = serviceUser.updateUserByEmail(userDTO,email);
        EmailModel emailModel = new EmailModel(email,"Your Account is Updated Successfully",userModel.getFirstName());
        serviceEmail.sendEmail(emailModel);
        ResponseBookDTO responseDto = new ResponseBookDTO("existing user data update successfully by email",userModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    //creating Api for Updating User details by Token
    @PutMapping("/updateByToken")
    public ResponseEntity<ResponseBookDTO> updateUserByToken(@Valid @RequestBody UserDTO userDTO,@RequestParam String token){
        UserModel userModel = serviceUser.updateUserByToken(userDTO,token);
//        EmailModel emailModel = new EmailModel(userModel.getEmail(),"Your Account is Updated Successfully",userModel.getFirstName());
//        serviceEmail.sendEmail(emailModel);
        ResponseBookDTO responseDto = new ResponseBookDTO("existing user data update successfully by email",userModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;
    }

    //create Api for delete record
    @DeleteMapping("/deleteRecord/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        serviceUser.deleteUserData(id);
        return new ResponseEntity<>("delete the data successfully",HttpStatus.OK);

    }
    //Create Api for  user login
    @PostMapping("/loginUser")
    public ResponseEntity<ResponseBookDTO> loginUser(@Valid @RequestBody LoginDTO loginDTO){
        String userModel = serviceUser.loginUser(loginDTO);
        ResponseBookDTO responseDTO = new ResponseBookDTO("Logged in  Successfully",userModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseDTO,HttpStatus.OK);
        return response;
    }
    //Create Api for Changing or updating password using email

    @PutMapping("/forgetPassword/{email}")
    public  ResponseEntity<ResponseBookDTO> forgetPassword(@Valid @RequestBody UserDTO userDTO, @PathVariable String email){
        UserModel userModel=serviceUser.forgetPassword(userDTO,email);
        ResponseBookDTO responseDto = new ResponseBookDTO("Password successfully changed",userModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        return response;

    }
}
