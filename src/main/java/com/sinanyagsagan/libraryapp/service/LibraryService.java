package com.sinanyagsagan.libraryapp.service;

import com.sinanyagsagan.libraryapp.dto.BookStockSaveRequestDto;
import com.sinanyagsagan.libraryapp.dto.LibraryTakeInRequestDto;
import com.sinanyagsagan.libraryapp.entity.Book;
import com.sinanyagsagan.libraryapp.entity.BookStock;
import com.sinanyagsagan.libraryapp.entity.Customer;
import com.sinanyagsagan.libraryapp.entity.Library;
import com.sinanyagsagan.libraryapp.enums.BookOffice;
import com.sinanyagsagan.libraryapp.repository.LibraryRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class LibraryService {

    private final BookService bookService;
    private final CustomerService customerService;
    private final BookStockService bookStockService;

    private final LibraryRepository libraryRepository;

    public boolean takeIn(LibraryTakeInRequestDto libraryTakeInRequestDto) {

        Book book = bookService.getBookById(libraryTakeInRequestDto.getBookId());
        Customer customer = customerService.getCustomerById(libraryTakeInRequestDto.getCustomerId());
        Library library = new Library();
        try {
            library.setCustomer(customer);
            library.setBook(book);
            library.setBookOffice(BookOffice.valueOf
                    (String.valueOf(libraryTakeInRequestDto.getBookOffice())));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            List<BookStock> stockListId = bookStockService.findStockIdByBookOfficeAndBookId(book.getId(),
                    String.valueOf(libraryTakeInRequestDto.getBookOffice()));

            long stockId = stockListId.get(0).getId();
            int currentStock = stockListId.get(0).getBookStock();
            int updatedStock = currentStock - 1;

            BookStockSaveRequestDto updateBookStock = new BookStockSaveRequestDto();
            updateBookStock.setBookStock(updatedStock);


            bookStockService.updateBookStock(stockId,updateBookStock);
            libraryRepository.save(library);
        }
        return true;
    }
}
