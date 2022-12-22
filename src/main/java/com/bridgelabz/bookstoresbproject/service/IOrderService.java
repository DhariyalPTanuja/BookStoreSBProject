package com.bridgelabz.bookstoresbproject.service;

import com.bridgelabz.bookstoresbproject.dto.OrderDTO;
import com.bridgelabz.bookstoresbproject.model.OrderModel;

import java.util.List;

public interface IOrderService {
    public OrderModel insertOrder(OrderDTO orderDTO);

    public List<OrderModel> getAllOrderRecord();
    public OrderModel getById(Long orderId);
    public OrderModel cancelOrder(Long orderId);
    public OrderModel updateById(OrderDTO orderDTO, Long orderId );
}
