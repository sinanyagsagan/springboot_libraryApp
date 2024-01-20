package com.sinanyagsagan.libraryapp.service;

import com.sinanyagsagan.libraryapp.dto.BookSaveRequestDto;
import com.sinanyagsagan.libraryapp.entity.Book;
import com.sinanyagsagan.libraryapp.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.*;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    public boolean saveBook(BookSaveRequestDto bookSaveRequestDto){
        Book book = modelMapper.map(bookSaveRequestDto, Book.class);
        bookRepository.save(book);
        return true;
    }
    public boolean deleteBook(Long bookId) {
        if (bookRepository.existsById(bookId)){
            bookRepository.deleteById(bookId);
            return true;
        }else return false;
    }
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long bookId) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        return optionalBook.orElse(null);
    }
    public List<Book> searchByBookName(String bookName) {
        return bookRepository.findByBookNameContainingCustom(bookName);
    }
    public boolean updateBook(Long bookId, BookSaveRequestDto updatedBookDto) {
        Optional<Book> optionalExistBook = bookRepository.findById(bookId);
        if (optionalExistBook.isPresent()){
            Book existingBook = optionalExistBook.get();
            BeanUtils.copyProperties(updatedBookDto, existingBook, getNullPropertyNames(updatedBookDto));
            bookRepository.save(existingBook);
            return true;
        }
        return false;
    }
    private String[] getNullPropertyNames(Object source) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] propertyDescriptors = beanWrapper.getPropertyDescriptors();

        Set<String> nullProperties = new HashSet<>();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            String propertyName = propertyDescriptor.getName();
            Object propertyValue = beanWrapper.getPropertyValue(propertyName);
            if (propertyValue == null) {
                nullProperties.add(propertyName);
            }
        }
        return nullProperties.toArray(new String[0]);
    }
}
