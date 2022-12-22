package com.bridgelabz.bookstoresbproject.repository;

import com.bridgelabz.bookstoresbproject.model.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel,Long> {
}
