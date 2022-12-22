package com.bridgelabz.bookstoresbproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class LoginDTO {
    @NotBlank(message = "Email is mandatory")
    public String email;
    @NotBlank(message = "password is mandatory")
    public String password;
}
