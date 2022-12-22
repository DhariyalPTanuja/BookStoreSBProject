package com.bridgelabz.bookstoresbproject.repository;

import com.bridgelabz.bookstoresbproject.model.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartModel,Long> {
}
