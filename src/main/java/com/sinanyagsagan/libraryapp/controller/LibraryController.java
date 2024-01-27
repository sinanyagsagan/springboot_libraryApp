package com.sinanyagsagan.libraryapp.controller;

import com.sinanyagsagan.libraryapp.dto.BookStockSaveRequestDto;
import com.sinanyagsagan.libraryapp.dto.LibraryTakeInRequestDto;
import com.sinanyagsagan.libraryapp.service.LibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
public class LibraryController {

    private final LibraryService libraryService;

    @PostMapping("/takeIn")
    public ResponseEntity<Boolean> saveStock(@RequestBody LibraryTakeInRequestDto libraryTakeInRequestDto){
        boolean isSaveTakeIn = libraryService.takeIn(libraryTakeInRequestDto);
        return new ResponseEntity<>(isSaveTakeIn, HttpStatus.OK);
    }
}
