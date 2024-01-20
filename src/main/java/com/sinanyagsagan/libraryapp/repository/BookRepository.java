package com.sinanyagsagan.libraryapp.repository;

import com.sinanyagsagan.libraryapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository <Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.bookName LIKE %:keyword%")
    List<Book> findByBookNameContainingCustom(@Param("keyword") String keyword);

}
