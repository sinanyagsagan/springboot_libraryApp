package com.sinanyagsagan.libraryapp.repository;

import com.sinanyagsagan.libraryapp.entity.BookStock;
import com.sinanyagsagan.libraryapp.enums.BookOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookStockRepository extends JpaRepository<BookStock, Long> {

    @Query("SELECT b FROM BookStock b WHERE b.bookOffice = :office")
    List<BookStock> findStockByBookOffice(@Param("office") BookOffice office);

    @Query("SELECT b FROM BookStock b WHERE b.book.id = :bookId")
    List<BookStock> findStockByBookId(@Param("bookId") Long bookId);


    @Query("SELECT bs FROM BookStock bs WHERE bs.bookOffice = :office AND bs.book.id = :bookId")
    List<BookStock> findStockIdByBookOfficeAndBookId(
            @Param("office") BookOffice office,
            @Param("bookId") Long bookId
    );


}
