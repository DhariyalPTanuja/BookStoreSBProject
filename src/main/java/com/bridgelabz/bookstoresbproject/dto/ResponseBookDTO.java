package com.bridgelabz.bookstoresbproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ResponseBookDTO {
    private String msg;
    private Object data;
}
