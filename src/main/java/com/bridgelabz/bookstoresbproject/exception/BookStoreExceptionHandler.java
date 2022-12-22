package com.bridgelabz.bookstoresbproject.exception;

import com.bridgelabz.bookstoresbproject.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BookStoreExceptionHandler {
    private static final String message= "Exception while processing RESt Request";
    @ExceptionHandler(BookStoreException.class)
    public ResponseEntity<ResponseDTO> handleBookStoreException(BookStoreException exception){
        ResponseDTO responseDTO = new ResponseDTO(message,exception.getMessage(),null);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
