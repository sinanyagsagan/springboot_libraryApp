package com.sinanyagsagan.libraryapp.service;

import com.sinanyagsagan.libraryapp.dto.BookStockSaveRequestDto;
import com.sinanyagsagan.libraryapp.entity.Book;
import com.sinanyagsagan.libraryapp.entity.BookStock;
import com.sinanyagsagan.libraryapp.enums.BookOffice;
import com.sinanyagsagan.libraryapp.repository.BookStockRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookStockService {

    private final BookStockRepository bookStockRepository;
    private final ModelMapper modelMapper;
    private final BookService bookService;
    public boolean saveStock(BookStockSaveRequestDto bookStockSaveRequestDto) {
        Book book = bookService.getBookById((long) bookStockSaveRequestDto.getBookId());
        BookStock bookStock = modelMapper.map(bookStockSaveRequestDto, BookStock.class);
        bookStock.setBook(book);
        bookStockRepository.save(bookStock);
        return true;
    }

    public boolean deleteStock(Long bookStockId){
        if (bookStockRepository.existsById(bookStockId)){
            bookStockRepository.deleteById(bookStockId);
            return true;
        }else return false;
    }

    public List<BookStock> getAllStocks() {return bookStockRepository.findAll();}

    public List<BookStock> getStockByOffice(String bookOffice) {
        return bookStockRepository.findStockByBookOffice(BookOffice.valueOf(bookOffice));
    }

    public List<BookStock> getStockByBookId(Long bookId) {
        return bookStockRepository.findStockByBookId(bookId);
    }

    public boolean updateBookStock(Long stockId, BookStockSaveRequestDto updateStock) {
        Optional<BookStock> optionalExistStock = bookStockRepository.findById(stockId);
        if (optionalExistStock.isPresent()){
            BookStock existingStock = optionalExistStock.get();
            BeanUtils.copyProperties(updateStock, existingStock, getNullPropertyNames(updateStock));
            bookStockRepository.save(existingStock);
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
