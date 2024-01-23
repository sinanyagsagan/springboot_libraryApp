package com.sinanyagsagan.libraryapp.controller;

import com.sinanyagsagan.libraryapp.dto.CustomerDeleteRequestDto;
import com.sinanyagsagan.libraryapp.dto.CustomerSaveRequestDto;
import com.sinanyagsagan.libraryapp.entity.Customer;
import com.sinanyagsagan.libraryapp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/saveCustomer")
    public ResponseEntity<Boolean> saveCustomer(@RequestBody CustomerSaveRequestDto customerSaveRequestDto){
        boolean isSaveCustomer = customerService.saveCustomer(customerSaveRequestDto);
        return new ResponseEntity<>(isSaveCustomer, HttpStatus.OK);
    }

    @GetMapping("/getAllCustomer")
    public ResponseEntity<List<Customer>> getAllCustomer(){
        List<Customer> allCustomer = customerService.getAllCustomer();
        if (allCustomer.isEmpty()) return ResponseEntity.noContent().build();
        else return ResponseEntity.ok(allCustomer);
    }

    @GetMapping("/getById")
    public ResponseEntity<Customer> getCustomerId(@RequestParam Long customerId){
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) return new ResponseEntity<>(customer,HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/deleteCustomer")
    public ResponseEntity<Boolean> deleteCustomer(@RequestBody CustomerDeleteRequestDto customerDeleteRequestDto){
        boolean isDeleted = customerService.deleteCustomer(customerDeleteRequestDto.getCustomerId());
        if (isDeleted) return new ResponseEntity<>(true, HttpStatus.OK);
        else return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @PutMapping("updateCustomer/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId,
                                                   @RequestBody CustomerSaveRequestDto updateCustomer){
        boolean isUpdated = customerService.updateCustomer(customerId, updateCustomer);
        if (isUpdated) return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
