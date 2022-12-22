package com.bridgelabz.bookstoresbproject.service;

import com.bridgelabz.bookstoresbproject.dto.BookDTO;
import com.bridgelabz.bookstoresbproject.model.BookModel;

import java.util.List;

public interface IBookService {

    public BookModel insertBook(BookDTO bookDTO);
    public List<BookModel> getAllBookRecord();
    public BookModel getBookRecordById(Long id);
    public String deleteBook(Long id);
    public BookModel updateBookRecordById(BookDTO bookDTO,Long id);
    public BookModel searchByBookName(String bookName);
    public BookModel updateBookQuantity(BookDTO bookDTO,Long Id,int quantity);

    public List<BookModel> sortingAsce();
    public List<BookModel> sortingDesc();
}
