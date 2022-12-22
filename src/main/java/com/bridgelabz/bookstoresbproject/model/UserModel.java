package com.bridgelabz.bookstoresbproject.model;

import com.bridgelabz.bookstoresbproject.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Table(name = "User_Information")
public @Data class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_ID")
    private long userID;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private LocalDate dob;
    private String password;
    private String confirmPassword;
    public void updateUserModel(UserDTO userDTO) {
        this.firstName = userDTO.firstName;
        this.lastName = userDTO.lastName;
        this.email = userDTO.email;
        this.address = userDTO.address;
        this.dob = userDTO.dob;
        this.password = userDTO.password;
        this.confirmPassword = userDTO.confirmPassword;
    }


    public UserModel(UserDTO userDTO) {
        this.updateUserModel(userDTO);

    }
}
