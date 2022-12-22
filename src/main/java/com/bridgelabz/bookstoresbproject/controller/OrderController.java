package com.bridgelabz.bookstoresbproject.controller;


import com.bridgelabz.bookstoresbproject.dto.OrderDTO;
import com.bridgelabz.bookstoresbproject.dto.ResponseBookDTO;
import com.bridgelabz.bookstoresbproject.dto.ResponseDTO;
import com.bridgelabz.bookstoresbproject.model.EmailModel;
import com.bridgelabz.bookstoresbproject.model.OrderModel;
import com.bridgelabz.bookstoresbproject.service.EmailService;
import com.bridgelabz.bookstoresbproject.service.IOrderService;
import com.bridgelabz.bookstoresbproject.util.TokenUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/bookStoreSBProject/order")
public class OrderController {
    //create dependency  injection for IOrderService  interface
    @Autowired
    IOrderService serviceOrder;
    //create dependency  injection for TokenUtil class
    @Autowired
    TokenUtil tokenUtil;
    //create dependency  injection for EmailService class
    @Autowired
    EmailService serviceEmail;
    // Create Api for insert the new Book record
    @PostMapping("/insert")
    public ResponseEntity<ResponseBookDTO> insertOrder(@RequestBody OrderDTO orderDTO){
        OrderModel orderModel = serviceOrder.insertOrder(orderDTO);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Record inserted in data base",orderModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;
    }
    // Create Api for insert the new Order record
    @PostMapping("/createOrder")
    public ResponseEntity<ResponseDTO> createOrder(@Valid @RequestBody OrderDTO orderDTO){
        OrderModel orderModel = serviceOrder.insertOrder(orderDTO);
        String token = tokenUtil.createToken(orderModel.getOrderId());
        EmailModel emailModel = new EmailModel(orderModel.getUser().getEmail(),
                "Order Placed successfully", "user " + orderModel + " Token "+token);
        serviceEmail.sendEmail(emailModel);
        ResponseDTO responseDto = new ResponseDTO(" New order placed successfully",orderModel,token);
        ResponseEntity<ResponseDTO> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
        log.info(token);
        return response;

    }
    //Creating Api for retrieve All Order record
    @GetMapping("/getAll")
    public ResponseEntity<ResponseBookDTO> getAllOrderRecord(){
        List<OrderModel> orderModelList = serviceOrder.getAllOrderRecord();
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("Retrieve all records",orderModelList);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;
    }
    //Creating Api for retrieve specific order record
    @GetMapping("/getById/{id}")
    public ResponseEntity<ResponseBookDTO> getOrderRecordById(@PathVariable Long id){
        OrderModel orderModel = serviceOrder.getById(id);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("fetch specific book record ",orderModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;
    }

    //Creating Api for update order record
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseBookDTO> updateOrderRecordById(@Valid @RequestBody OrderDTO orderDTO,@PathVariable Long id){
        OrderModel orderModel = serviceOrder.updateById(orderDTO,id);
        EmailModel emailModel = new EmailModel(orderModel.getUser().getEmail(),
                "Order update successfully", "user " + orderModel );
        serviceEmail.sendEmail(emailModel);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("existing order record update successfully",orderModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;

    }

    //Creating Api for cancel order record
    @PutMapping("/cancel/{id}")
    public ResponseEntity<ResponseBookDTO> cancelOrderRecordById(@Valid@PathVariable Long id){
        OrderModel orderModel = serviceOrder.cancelOrder(id);
        EmailModel emailModel = new EmailModel(orderModel.getUser().getEmail(),
                "Order update successfully", "user " + orderModel );
        serviceEmail.sendEmail(emailModel);
        ResponseBookDTO responseBookDTO = new ResponseBookDTO("existing order record cancel successfully",orderModel);
        ResponseEntity<ResponseBookDTO> response = new ResponseEntity<>(responseBookDTO, HttpStatus.OK);
        return response;

    }
}
