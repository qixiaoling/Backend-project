package nl.novi.Backend.controller;

import nl.novi.Backend.model.Customer;
import nl.novi.Backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CustomerController {


    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }
//hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('customer:read')")
    public List<Customer> getAllCustomers(){

        return customerService.getAllCustomers();
    }
    @PostMapping("/customers")
    @PreAuthorize("hasAuthority('customer:write')")
    public List<Customer> addCustomers(@RequestBody Customer customer){
        return customerService.addCustomers(customer);
    }


}
