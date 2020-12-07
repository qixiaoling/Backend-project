package nl.novi.Backend.controller;

import nl.novi.Backend.model.Customer;
import nl.novi.Backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class CustomerController {


    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }


    @GetMapping("/customers")
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomers();
    }
    @PostMapping("/customers")
    public List<Customer> addCustomer(@RequestBody Customer customer){
        return customerService.addCustomers(customer);
    }


}
