package com.bridgelabz.bookstoresbproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO {
    private String msg;
    private Object data;
    private String token;
}
