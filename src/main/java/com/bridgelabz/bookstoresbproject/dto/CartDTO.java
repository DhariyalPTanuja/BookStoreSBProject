package com.bridgelabz.bookstoresbproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartDTO {

    @NotNull(message = "User id can not be null")
    public Long user;
    @NotNull(message = "book id can not be null")
    public Long book;
    @NotNull(message = "quantity not null")
    public int cartQuantity;
}
