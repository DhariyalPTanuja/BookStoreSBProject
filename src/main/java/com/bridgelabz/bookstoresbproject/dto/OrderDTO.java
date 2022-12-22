package com.bridgelabz.bookstoresbproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
@Data
public class OrderDTO {
    @PastOrPresent(message = "order date should be past or present")
    public LocalDate date;

    @NotNull(message = "order quantity can not be null")
    public int orderQuantity;
    @NotNull(message = "price can not be null")
    public double orderPrice;

    @NotNull(message = "address can not be null")
    public String deliveryAddress;
    @NotNull(message = "User id can not be null")
    public Long user;
    @NotNull(message = "book id can not be null")
    public List<Long> book;
    public boolean cancel;




    public boolean isCancel() {
        return false;
    }

    public OrderDTO(LocalDate date, int orderQuantity, double orderPrice, String deliveryAddress, Long user, List<Long> book, boolean cancel) {
        this.date = LocalDate.now();
        this.orderQuantity = orderQuantity;
        this.orderPrice = orderPrice;
        this.deliveryAddress = deliveryAddress;
        this.user = user;
        this.book = book;
        this.cancel = cancel;
    }
}
