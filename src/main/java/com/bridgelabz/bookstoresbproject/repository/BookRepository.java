package com.bridgelabz.bookstoresbproject.repository;

import com.bridgelabz.bookstoresbproject.model.BookModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<BookModel,Long> {

    @Query(value = "select * from book_info where book_info.book_name=:bookName", nativeQuery = true)
    BookModel findRecordByBook(String bookName);
    @Query(value = "select * from book_info order by book_info.price", nativeQuery = true)
    List<BookModel> sortingAsceByPrice();
    @Query(value = "select * from book_info order by book_info.price DESC ", nativeQuery = true)
    List<BookModel> sortingDescByPrice();
}
