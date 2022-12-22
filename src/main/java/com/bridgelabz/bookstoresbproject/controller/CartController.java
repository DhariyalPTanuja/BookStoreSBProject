package com.bridgelabz.bookstoresbproject.controller;

import com.bridgelabz.bookstoresbproject.dto.CartDTO;
import com.bridgelabz.bookstoresbproject.dto.ResponseBookDTO;
import com.bridgelabz.bookstoresbproject.model.CartModel;
import com.bridgelabz.bookstoresbproject.service.ICartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookStoreSBProject/cart")
public class CartController {

    @Autowired
    ICartService serviceCart;

    @PostMapping("/insert")
    public ResponseEntity<ResponseBookDTO> insertInCart(@Valid @RequestBody CartDTO cartDTO){
        CartModel cartModel = serviceCart.insert(cartDTO);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Record inserted in data base",cartModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;
    }
    //Creating Api for retrieve All book record
    @GetMapping("/getAll")
    public ResponseEntity<ResponseBookDTO> getAllRecord(){
        List<CartModel> cartList = serviceCart.getAllCartDetail();
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Retrieve all records",cartList);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;
    }
    //Creating Api for retrieve specific cart record
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseBookDTO> getRecordById(@PathVariable Long id){
        CartModel cartModel = serviceCart.getCartDetailById(id);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("fetch specific book record ",cartModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;
    }
    //Creating Api for deleting specific cart detail
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteRecord(@PathVariable Long id){
        serviceCart.delete(id);
        return new ResponseEntity<>("delete the data successfully",HttpStatus.OK);
    }
    //Creating Api for update book record
    @PutMapping ("/update/{id}")
    public ResponseEntity<ResponseBookDTO> updateBookRecordById(@Valid @RequestBody CartDTO cartDTO,@PathVariable Long id){
        CartModel cartModel = serviceCart.updateCartDetailById(cartDTO,id);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("existing cart record update successfully",cartModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;

    }
    //Creating Api for quantity update in Record by id
//
    //Creating Api for quantity update in Record by id
    @PutMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseBookDTO> updateBookQuantity(@PathVariable Long id,@RequestParam int quantity) {
        CartModel cartModel = serviceCart.updateQuantity(id,quantity);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("existing quantity in cart  update successfully", cartModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;
    }
}
