package com.sinanyagsagan.libraryapp.dto;

import com.sinanyagsagan.libraryapp.enums.BookOffice;
import lombok.Data;

@Data
public class BookStockSaveRequestDto {
    private int bookId;
    private int bookStock;
    private BookOffice bookOffice;
}
