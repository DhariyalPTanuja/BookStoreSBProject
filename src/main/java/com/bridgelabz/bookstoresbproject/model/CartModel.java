package com.bridgelabz.bookstoresbproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@Entity
@Table(name = "Cart_info")
public class CartModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_Id")
    private  Long cartID;

    @OneToOne
    @JoinColumn(name = "user_Id")
    private UserModel userID;
    @ManyToOne
    @JoinColumn(name = "book_Id")
    private BookModel bookID;
    private int cartQuantity;

    public CartModel(UserModel userID, BookModel bookID, int cartQuantity) {
        this.userID = userID;
        this.bookID = bookID;
        this.cartQuantity = cartQuantity;
    }
}
