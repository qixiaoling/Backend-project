package nl.novi.Backend.service;


import nl.novi.Backend.model.Customer;
import nl.novi.Backend.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers(){
        List<Customer> customers = new ArrayList<>();
         customerRepository.findAll().forEach(customers::add);
         return customers;
    }

    public List<Customer> addCustomers(Customer customer){
        List<Customer> customers = new ArrayList<>();
        customerRepository.save(customer);
        return customers;
    }

    public Customer getCustomerById(Long customerId){
        Optional<Customer> possibleCustomer = customerRepository.findById(customerId);
        if(possibleCustomer.isPresent()){
            return possibleCustomer.get();
        }
        return null;
    }

    public Customer updateCustomerById(Long customerId, Customer customer){
        Optional<Customer> possibleCustomer = customerRepository.findById(customerId);
        if(possibleCustomer.isPresent()){
            Customer aCustomer = possibleCustomer.get();
            return aCustomer;
        }
        return null;
    }



}
