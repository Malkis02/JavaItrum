package com.example.task3.services;

import com.example.task3.exceptions.NotFoundException;
import com.example.task3.models.Customer;
import com.example.task3.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {

    private final CustomerRepository customerRepository;


    public Customer addCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Transactional
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElseThrow(()->new NotFoundException("Customer with this id does not exist"));
    }

    public Customer updateCustomer( Long id, Customer customer) {
        if (!customerRepository.existsById(id)) {
            throw new NotFoundException("Customer with this id does not exist");

        }
        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new NotFoundException("Customer with this id does not exist");
        }
        customerRepository.deleteById(id);
    }

    public long getCustomerBalance(Long id){
        Customer customer = customerRepository.findById(id).orElseThrow(()->new NotFoundException("Customer with this is does not exist"));
        return customer.getBalance();
    }
}
