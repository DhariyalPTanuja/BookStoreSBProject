package com.bridgelabz.bookstoresbproject.service;

import com.bridgelabz.bookstoresbproject.dto.CartDTO;
import com.bridgelabz.bookstoresbproject.model.CartModel;

import java.util.List;

public interface ICartService {

    public CartModel insert(CartDTO cartDTO);
    public List<CartModel> getAllCartDetail();
    public CartModel getCartDetailById(Long id);
    public String delete(Long id);
    public CartModel updateCartDetailById(CartDTO cartDTO, Long id);
    public CartModel updateQuantity( Long id,int quantity);
}
