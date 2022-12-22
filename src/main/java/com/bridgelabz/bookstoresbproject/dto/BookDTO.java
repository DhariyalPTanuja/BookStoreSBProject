package com.bridgelabz.bookstoresbproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDTO {
    @NotBlank(message = "Book Name not empty")
    public String bookName;
    @NotBlank(message = "author name not empty")
    public String authorName;
    @NotBlank(message = "Book Description not empty")
    public String bookDescription;
    public String bookImg;
    @NotNull(message = "price not null")
    public Double price;
    @NotNull(message = "quantity not null")
    public int quantity;

}
