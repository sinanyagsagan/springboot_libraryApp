package com.sinanyagsagan.libraryapp.controller;

import com.sinanyagsagan.libraryapp.dto.BookStockDeleteRequestDto;
import com.sinanyagsagan.libraryapp.dto.BookStockSaveRequestDto;
import com.sinanyagsagan.libraryapp.entity.BookStock;
import com.sinanyagsagan.libraryapp.service.BookStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookStock")
@RequiredArgsConstructor
public class BookStockController {

    private final BookStockService bookStockService;

    @PostMapping("/saveStock")
    public ResponseEntity<Boolean> saveStock(@RequestBody BookStockSaveRequestDto bookStockSaveRequestDto){

        boolean isSaveStockBook = bookStockService.saveStock(bookStockSaveRequestDto);
        return new ResponseEntity<>(isSaveStockBook, HttpStatus.OK);
    }
    @PostMapping("/deleteStock")
    public ResponseEntity<Boolean> deleteBook(@RequestBody BookStockDeleteRequestDto bookStockDeleteRequestDto){
        boolean isDeleted = bookStockService.deleteStock(bookStockDeleteRequestDto.getBookStockId());
        if (isDeleted) return new ResponseEntity<>(true,HttpStatus.OK);
        else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllStock")
    public ResponseEntity<List<BookStock>> getAllStocks(){
        List<BookStock> allStocks = bookStockService.getAllStocks();
        if (allStocks.isEmpty()) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok(allStocks);
    }

    @GetMapping("/getByStockOffice/{bookOffice}")
    public ResponseEntity<List<BookStock>> getByStockOffice(@PathVariable String bookOffice) {

        List<BookStock> searchResult = bookStockService.getStockByOffice(bookOffice);
        if (searchResult.isEmpty()){
            return ResponseEntity.noContent().build();
        }else return ResponseEntity.ok(searchResult);

    }

    @GetMapping("/getByStockBookId/{bookId}")
    public ResponseEntity<List<BookStock>> getByStockOffice(@PathVariable Long bookId) {
        List<BookStock> searchResult = bookStockService.getStockByBookId(bookId);
        if (searchResult.isEmpty()){
            return ResponseEntity.noContent().build();
        }else return ResponseEntity.ok(searchResult);
    }
    @PutMapping("updateStock/{stockId}")
    public ResponseEntity<BookStock> updateBook(@PathVariable Long stockId,
                                                @RequestBody BookStockSaveRequestDto updateStock){
        boolean isUpdated = bookStockService.updateBookStock(stockId, updateStock);
        if (isUpdated)return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
