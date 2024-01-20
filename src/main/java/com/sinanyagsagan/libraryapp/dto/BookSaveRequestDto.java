package com.sinanyagsagan.libraryapp.dto;

import com.sinanyagsagan.libraryapp.enums.BookCategory;
import lombok.Data;

@Data
public class BookSaveRequestDto {
    private String bookName;
    private String bookAuthor;
    private String bookISBN;
    private String bookLanguage;
    private Long bookVolumeNo;
    private BookCategory bookCategory;
    private String publisher;
}
