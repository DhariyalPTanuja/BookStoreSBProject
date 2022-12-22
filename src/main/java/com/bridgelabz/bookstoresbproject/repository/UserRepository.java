package com.bridgelabz.bookstoresbproject.repository;

import com.bridgelabz.bookstoresbproject.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel,Long> {
    @Query(value = "select * from User_Information where User_Information.email=:email", nativeQuery = true)
    UserModel findDataByMail(String email);

    @Query(value = "select * from User_Information where User_Information.email=:email and User_Information.password=:password  ", nativeQuery = true)
    UserModel checkLoginData(String email, String password);

    @Query(value = "update User_Information set User_Information.password=:password,User_Information.confirm_password=:confirmPassword where User_Information.email=:email ", nativeQuery = true)
    UserModel changeUserPassword(String password, String confirmPassword, String email);
}
