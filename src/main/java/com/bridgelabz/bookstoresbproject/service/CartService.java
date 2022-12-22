package com.bridgelabz.bookstoresbproject.service;

import com.bridgelabz.bookstoresbproject.dto.CartDTO;
import com.bridgelabz.bookstoresbproject.exception.BookStoreException;
import com.bridgelabz.bookstoresbproject.model.BookModel;
import com.bridgelabz.bookstoresbproject.model.CartModel;
import com.bridgelabz.bookstoresbproject.model.UserModel;
import com.bridgelabz.bookstoresbproject.repository.BookRepository;
import com.bridgelabz.bookstoresbproject.repository.CartRepository;
import com.bridgelabz.bookstoresbproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{
    List<CartModel> cartList = new ArrayList<>();
    //create dependency  injection for CartRepository class
    @Autowired
    CartRepository repoCart;
    //create dependency  injection for UserRepository class
    @Autowired
    UserRepository repoUser;
    //create dependency  injection for BookRepository class
    @Autowired
    BookRepository repoBook;
    //Apply Logic for  insert  cart Details in the database
    @Override
    public CartModel insert(CartDTO cartDTO) {
        Optional<BookModel> checkBookId = repoBook.findById(cartDTO.getBook());
        Optional<UserModel> checkUserId = repoUser.findById(cartDTO.getUser());
        if (checkUserId.isPresent() && checkBookId.isPresent()) {
            //CartModel cartModel = new CartModel(cartDTO);
            CartModel cartModel = new CartModel(checkUserId.get(),checkBookId.get(),cartDTO.getCartQuantity());
            return repoCart.save(cartModel);
        } else
            throw new BookStoreException("UserId or Book Id is not found! Please provide valid id");
    }
    //Apply Logic for getting all cart Detail present in the database
    @Override
    public List<CartModel> getAllCartDetail() {
        cartList = repoCart.findAll();
        return cartList;
    }
    //Apply Logic for getting particular cart record which will be found by id
    @Override
    public CartModel getCartDetailById(Long id) {
        Optional<CartModel> checkCart = repoCart.findById(id);
        if(checkCart.isPresent())
            return checkCart.get();
        else
            throw new BookStoreException("id is not found");
    }
    //Apply Logic for deleting particular cart detail which will be deleted by id
    @Override
    public String delete(Long id) {
        Optional<CartModel> checkID =repoCart.findById(id);
        if (checkID.isPresent())
            repoCart.deleteById(id);
        else
            throw new BookStoreException("Id is not found");
        return "data delete successfully";
    }
    //Apply Logic for updating particular cart detail by id
    @Override
    public CartModel updateCartDetailById(CartDTO cartDTO, Long id) {
        Optional<CartModel> checkId = repoCart.findById(id);
        Optional<BookModel> checkBookId = repoBook.findById(cartDTO.getBook());
        Optional<UserModel> checkUserId = repoUser.findById(cartDTO.getUser());
        if (checkId.isPresent()) {
            if (checkUserId.isPresent() && checkBookId.isPresent()) {
                CartModel cartModel = new CartModel(checkUserId.get(),checkBookId.get(),cartDTO.getCartQuantity());
                cartModel.setCartID(id);
                CartModel updateCartModel = repoCart.save(cartModel);
                return updateCartModel;
            } else
                throw new BookStoreException("Book ID or User Id is not Found!! update failed");
        }else
            throw new BookStoreException("Id is not Found!! update failed");
    }
    //Apply Logic for updating only quantity to particular cart detail by id
    @Override
    public CartModel updateQuantity(Long id, int quantity) {
        Optional<CartModel> checkCartId = repoCart.findById(id);
        Optional<UserModel> checkUserId = repoUser.findById(checkCartId.get().getUserID().getUserID());
       // Optional<UserModel> checkUserId = repoUser.findById(checkId.get().getUserID().getUserID());
        Optional<BookModel> checkBookId = repoBook.findById(checkCartId.get().getBookID().getBookID());
        int bookQuantity = checkBookId.get().getQuantity();
        if (checkCartId.isPresent()) {
            if(quantity < bookQuantity){
                CartModel cartModel = new CartModel(checkUserId.get(),checkBookId.get(),checkCartId.get().getCartQuantity());
                cartModel.setCartID(id);
                cartModel.setCartQuantity(quantity);
                return repoCart.save(cartModel);
            }else
                throw new BookStoreException("book quantity " +quantity + " is not available. only " +bookQuantity + "is available");
        } else
            throw new BookStoreException("Id is not Found!! update failed");
    }
    }

