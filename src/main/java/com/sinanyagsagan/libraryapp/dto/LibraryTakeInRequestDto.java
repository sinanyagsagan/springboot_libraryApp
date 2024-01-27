package com.sinanyagsagan.libraryapp.dto;

import com.sinanyagsagan.libraryapp.enums.BookOffice;
import lombok.Data;

@Data
public class LibraryTakeInRequestDto {
    private Long customerId;
    private Long bookId;
    private BookOffice bookOffice;
}
