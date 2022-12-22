package com.bridgelabz.bookstoresbproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_info")
public class OrderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private LocalDate date ;
    private double orderPrice;
    private int orderQuantity;
    private String deliveryAddress;
    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_user_id")
    private UserModel user;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_book_id")
    private List<BookModel> book = new ArrayList<>();
    private boolean cancel;

    public OrderModel(LocalDate date, double totalPrice, int orderQuantity, String deliveryAddress, UserModel userModel, List<BookModel> bookList, boolean cancel) {
        this.date = LocalDate.now();
        this.orderPrice = totalPrice;
        this.orderQuantity = orderQuantity;
        this.deliveryAddress = deliveryAddress;
        this.user = userModel;
        this.book = bookList;
        this.cancel = cancel;
    }
}
