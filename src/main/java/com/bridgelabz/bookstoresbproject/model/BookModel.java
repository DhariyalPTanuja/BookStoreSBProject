package com.bridgelabz.bookstoresbproject.model;

import com.bridgelabz.bookstoresbproject.dto.BookDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Book_info")
public class BookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;
    private String bookName;
    private String authorName;
    private String bookDescription;
    private String bookImg;
    private Double price;
    private int quantity;
    public void updateBookModel(BookDTO bookDTO) {
        this.bookName = bookDTO.bookName;
        this.authorName =  bookDTO.authorName;
        this.bookDescription =  bookDTO.bookDescription;
        this.bookImg = bookDTO. bookImg;
        this.price = bookDTO. price;
        this.quantity =  bookDTO.quantity;
    }
    public BookModel(BookDTO bookDTO){
        this.updateBookModel(bookDTO);
    }
}
