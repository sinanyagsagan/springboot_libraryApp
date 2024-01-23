package com.sinanyagsagan.libraryapp.service;

import com.sinanyagsagan.libraryapp.dto.CustomerSaveRequestDto;
import com.sinanyagsagan.libraryapp.entity.Customer;
import com.sinanyagsagan.libraryapp.repository.CustomerRepository;
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
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final ModelMapper modelMapper;

    public boolean saveCustomer(CustomerSaveRequestDto customerSaveRequestDto) {
        Customer customer = modelMapper.map(customerSaveRequestDto, Customer.class);
        customerRepository.save(customer);
        return true;
    }
    public List<Customer> getAllCustomer() {return customerRepository.findAll();}
    public Customer getCustomerById(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        return optionalCustomer.orElse(null);
    }
    public boolean deleteCustomer(Long customerId) {
        if (customerRepository.existsById(customerId)){
            customerRepository.deleteById(customerId);
            return true;
        }else return false;
    }

    public boolean updateCustomer(Long customerId, CustomerSaveRequestDto updateCustomerDto) {
        Optional<Customer> optionalExistingCustomer = customerRepository.findById(customerId);
        if (optionalExistingCustomer.isPresent()){
            Customer existingCustomer = optionalExistingCustomer.get();
            BeanUtils.copyProperties(updateCustomerDto,existingCustomer,getNullPropertyNames(updateCustomerDto));
            customerRepository.save(existingCustomer);
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
