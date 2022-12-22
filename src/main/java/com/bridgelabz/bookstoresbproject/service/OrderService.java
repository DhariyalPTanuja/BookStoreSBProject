package com.bridgelabz.bookstoresbproject.service;

import com.bridgelabz.bookstoresbproject.dto.OrderDTO;
import com.bridgelabz.bookstoresbproject.exception.BookStoreException;
import com.bridgelabz.bookstoresbproject.model.BookModel;
import com.bridgelabz.bookstoresbproject.model.OrderModel;
import com.bridgelabz.bookstoresbproject.model.UserModel;
import com.bridgelabz.bookstoresbproject.repository.BookRepository;
import com.bridgelabz.bookstoresbproject.repository.OrderRepository;
import com.bridgelabz.bookstoresbproject.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Slf4j
@Service
public class OrderService implements IOrderService{

    //create dependency  injection for OrderRepository class
    @Autowired
    OrderRepository repoOrder;
    //create dependency  injection for BookRepository class
    @Autowired
    BookRepository repoBook;
    //create dependency  injection for UserRepository class
    @Autowired
    UserRepository repoUser;

    //Apply Logic for inserting Order Record
    @Override
    public OrderModel insertOrder(OrderDTO orderDTO) {
        Optional<UserModel> checkUser = repoUser.findById(orderDTO.getUser());
        List<BookModel> bookList= orderDTO.getBook().stream().map(book -> repoBook.findById(book).get()).collect(Collectors.toList());

        if(checkUser.isPresent() ){
            double totalPrice = calculateTotalprice(bookList,orderDTO);
            // update book quantity in book model
            for(int i = 0 ; i < bookList.size(); i++){
                BookModel book = bookList.get(i);
                book.setQuantity(book.getQuantity() - orderDTO.getOrderQuantity());
                repoBook.save(book);
                log.info("book quantity=" + book.getQuantity());
            }

            OrderModel order = new OrderModel(orderDTO.getDate(),totalPrice,orderDTO.getOrderQuantity(),orderDTO.getDeliveryAddress(),checkUser.get(),bookList,orderDTO.isCancel());
            repoOrder.save(order);
            return  order;
        }
        else
            throw new BookStoreException("user id or book id did not match !! please check and try again");
    }
    //Apply Logic for getting All Order Record
    @Override
    public List<OrderModel> getAllOrderRecord() {
        List<OrderModel> orderList = repoOrder.findAll();
        return  orderList;
    }
    //Apply Logic for getting particular order record which will be found by id
    @Override
    public OrderModel getById(Long orderId) {
        Optional<OrderModel> checkId = repoOrder.findById(orderId);
        if (checkId.isPresent())
            return checkId.get();
        else
            throw new BookStoreException("id is not present");
    }
    //Apply Logic for cancel particular book record which will be deleted by id
    @Override
    public OrderModel cancelOrder(Long orderId) {
        Optional<OrderModel> checkOrderId = repoOrder.findById(orderId);
        List<BookModel> bookList= checkOrderId.get().getBook();
        if (checkOrderId.isPresent()) {
            OrderModel order = repoOrder.findById(orderId).get();
            order.setCancel(true);
            // update book quantity in book model
            for(int i = 0 ; i < bookList.size(); i++){
                BookModel book = bookList.get(i);
                book.setQuantity(book.getQuantity() + checkOrderId.get().getOrderQuantity());
                repoBook.save(book);
                log.info("book quantity=" + book.getQuantity());
            }
            return repoOrder.save(order);
        }
        else
            throw new BookStoreException("id is not present");
    }
    //Apply Logic for updating particular order record by id
    @Override
    public OrderModel updateById(OrderDTO orderDTO, Long orderId) {
        Optional<OrderModel> checkOrderId = repoOrder.findById(orderId);
        Optional<UserModel> checkUser = repoUser.findById(orderDTO.getUser());
        if (checkOrderId.isPresent()) {
            List<BookModel> bookList = orderDTO.getBook().stream().map(book -> repoBook.findById(book).get()).collect(Collectors.toList());
            if (checkUser.isPresent()) {
                double totalPrice = calculateTotalprice(bookList, orderDTO);
                OrderModel order = new OrderModel(orderDTO.getDate(), totalPrice, orderDTO.getOrderQuantity(), orderDTO.getDeliveryAddress(), checkUser.get(), bookList, orderDTO.isCancel());
                order.setOrderId(orderId);
                OrderModel updateOrder = repoOrder.save(order);
                return updateOrder;
            } else
                throw new BookStoreException("user id did not match !! please check and try again");
        }
        else
            throw new BookStoreException("order id did not match update unsuccessful!! please check and try again");
    }

    // Apply logic to calculate total price
    public double calculateTotalprice(List<BookModel> bookList, OrderDTO orderDTO) {
        double totalPrice = 0;
        int i = 0;
        while (i < bookList.size()) {
            BookModel bookId = bookList.get(i);
            double bookPrice = bookId.getPrice();
            log.info("bookprice =" + i + " : " + bookPrice);
            int quantity = bookId.getQuantity();
            if (orderDTO.getOrderQuantity() <= quantity) {
                totalPrice = totalPrice + bookPrice * (orderDTO.getOrderQuantity());
                log.info("price =" + totalPrice);
                log.info("quantity=" + orderDTO.getOrderQuantity());
            } else
                throw new BookStoreException(" book quantity entered by user is greater than available quantity! check again");
            i++;
        }
        return  totalPrice;
    }
}
