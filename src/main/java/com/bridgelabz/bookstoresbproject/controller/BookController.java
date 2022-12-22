package com.bridgelabz.bookstoresbproject.controller;

import com.bridgelabz.bookstoresbproject.dto.BookDTO;
import com.bridgelabz.bookstoresbproject.dto.ResponseBookDTO;
import com.bridgelabz.bookstoresbproject.model.BookModel;
import com.bridgelabz.bookstoresbproject.service.IBookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookStoreSBProject/book")
public class BookController {

@Autowired
    IBookService serviceBook;
    //Create Api for insert the new Book record
    @PostMapping("/insert")
    public ResponseEntity<ResponseBookDTO> insertBookRecord(@Valid @RequestBody BookDTO bookDTO){
        BookModel bookModel = serviceBook.insertBook(bookDTO);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Record inserted in data base",bookModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;
    }
    //Creating Api for retrieve All book record
    @GetMapping("/getAll")
    public ResponseEntity<ResponseBookDTO> getAllBookRecord(){
        List<BookModel> bookModelList = serviceBook.getAllBookRecord();
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Retrieve all records",bookModelList);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;
    }
    //Creating Api for retrieve specific book record
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseBookDTO> getRecordById(@PathVariable Long id){
        BookModel bookModel = serviceBook.getBookRecordById(id);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("fetch specific book record ",bookModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;
    }
    //Creating Api for deleting specific book
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id){
        serviceBook.deleteBook(id);
        return new ResponseEntity<>("delete the data successfully",HttpStatus.OK);
    }
    //Creating Api for update book record
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseBookDTO> updateBookRecordById(@Valid @RequestBody BookDTO bookDTO,@PathVariable Long id){
        BookModel bookModel = serviceBook.updateBookRecordById(bookDTO,id);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("existing book record update successfully",bookModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;

    }
    //Creating Api for search  book Record by book name
    @GetMapping("/searchByBook")
    public ResponseEntity<ResponseBookDTO> searchBookRecord(@RequestParam String bookName){
        BookModel bookModel = serviceBook.searchByBookName(bookName);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Search specific book record",bookModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;
    }
    //Creating Api for quantity of  book  in Record by book id
    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseBookDTO> updateBookQuantity(@RequestBody BookDTO bookDTO,@PathVariable Long id,@RequestParam int quantity) {
        BookModel bookModel = serviceBook.updateBookQuantity(bookDTO,id,quantity);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("existing book quantity update successfully", bookModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;
    }
    //Creating Api for sorting book in ascending order of book price
    @GetMapping("/sortingAsce")
    public ResponseEntity<ResponseBookDTO> sortingRecordAsce(){
        List<BookModel> bookModelList = serviceBook.sortingAsce();
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Sorting all records in ascending order of book price",bookModelList);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;
    }
    //Creating Api for sorting book in descending order of book price
    @GetMapping("/sortingDesc")
    public ResponseEntity<ResponseBookDTO> sortingRecordDesc(){
        List<BookModel> bookModelList = serviceBook.sortingDesc();
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Sorting all records in  Descending order of book price",bookModelList);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;
    }
}
