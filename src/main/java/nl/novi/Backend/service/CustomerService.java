package nl.novi.Backend.service;


import com.sun.org.apache.xpath.internal.operations.Bool;
import nl.novi.Backend.model.Customer;
import nl.novi.Backend.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public ResponseEntity<?> addCustomers(Customer customer) {
        if (!(customer.getEmail() == null)) {
            if(customerRepository.existsByEmail(customer.getEmail()).equals(Boolean.FALSE)){
                customerRepository.save(customer);
                return ResponseEntity.ok().body("The customer is now added");
            }else{
                return ResponseEntity.badRequest().body(" Error, this customer already exists.");
            }}
        return ResponseEntity.badRequest().body("Please fill in the email address from the customer.");
    }

    public Customer getCustomerById(Long customerId){
        Optional<Customer> possibleCustomer = customerRepository.findById(customerId);
        if(possibleCustomer.isPresent()){
            return possibleCustomer.get();
        }
        return null;
    }

    public ResponseEntity<?> updateCustomerById(Long customerId, Customer customer){
        Optional<Customer> possibleCustomer = customerRepository.findById(customerId);
        if(possibleCustomer.isPresent()){
            possibleCustomer.get().setFirstName(customer.getFirstName());
            possibleCustomer.get().setLastName(customer.getLastName());
            possibleCustomer.get().setGender(customer.getGender());
            possibleCustomer.get().setEmail(customer.getEmail());
            customerRepository.save(possibleCustomer.get());
            return ResponseEntity.ok().body("The customer is successfully updated.");

        }
        return ResponseEntity.badRequest().body("Error, please check again.");
    }

    public ResponseEntity<?> deleteCustomerById(Long customerId){
        Optional<Customer> possibleCustomer = customerRepository.findById(customerId);
        if(possibleCustomer.isPresent()){
            customerRepository.deleteById(customerId);
            return ResponseEntity.ok().body("The customer is deleted successfully.");
        }
        return ResponseEntity.badRequest().body("Please check the customer id again.");
    }



}
