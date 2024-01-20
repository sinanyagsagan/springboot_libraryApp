package com.sinanyagsagan.libraryapp.controller;

import com.sinanyagsagan.libraryapp.dto.BookDeleteRequestDto;
import com.sinanyagsagan.libraryapp.dto.BookSaveRequestDto;
import com.sinanyagsagan.libraryapp.entity.Book;
import com.sinanyagsagan.libraryapp.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/saveBook")
    public ResponseEntity<Boolean> saveBook(@RequestBody BookSaveRequestDto bookSaveRequestDto){
        boolean isSaveBook = bookService.saveBook(bookSaveRequestDto);
        return new ResponseEntity<>(isSaveBook, HttpStatus.OK);
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> allBooks = bookService.getAllBooks();
        if (allBooks.isEmpty()) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/getById")
    public ResponseEntity<Book> getBookId(@RequestParam Long bookId){
        Book book = bookService.getBookById(bookId);
        if (book != null) return new ResponseEntity<>(book, HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getByBookName/{bookName}")
    public ResponseEntity<List<Book>> getByBookName(@PathVariable String bookName) {
        List<Book> searchResult = bookService.searchByBookName(bookName);
        if (searchResult.isEmpty()){
            return ResponseEntity.noContent().build();
        }else return ResponseEntity.ok(searchResult);
    }

    @PostMapping("/deleteBook")
    public ResponseEntity<Boolean> deleteBook(@RequestBody BookDeleteRequestDto bookDeleteRequestDto){
        boolean isDeleted = bookService.deleteBook(bookDeleteRequestDto.getBookId());
        if (isDeleted) return new ResponseEntity<>(true,HttpStatus.OK);
        else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @PutMapping("updateBook/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId,@RequestBody BookSaveRequestDto updateBook){
       boolean isUpdated = bookService.updateBook(bookId, updateBook);
       if (isUpdated)return new ResponseEntity<>(HttpStatus.OK);
       else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
