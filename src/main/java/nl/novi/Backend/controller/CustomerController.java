package nl.novi.Backend.controller;

import nl.novi.Backend.model.Customer;
import nl.novi.Backend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CustomerController {


    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService=customerService;
    }
//hasRole('ROLE_') hasAnyRole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')

    @GetMapping("/customers")
    @PreAuthorize("hasAnyAuthority('USER_FRO', 'ADMIN')")
    //@PreAuthorize("hasAnyRole('ROLE_USER_FRO', 'ROLE_ADMIN')")
    public List<Customer> getAllCustomers(){

        return customerService.getAllCustomers();
    }
    @PostMapping("/customers")
    @PreAuthorize("hasAnyAuthority('USER_FRO', 'ADMIN')")
    public List<Customer> addCustomers(@RequestBody Customer customer){
        return customerService.addCustomers(customer);
    }
    @GetMapping("/customers/{customerid}")
    @PreAuthorize("hasAnyAuthority('USER_FRO','ADMIN')")
    public Customer getCustomerById(@PathVariable("customerid") Long customerId){
        return customerService.getCustomerById(customerId);
    }
    @PutMapping("/customers/{customerid}")
    @PreAuthorize("hasAnyAuthority('USER_FRO','ADMIN')")
    public ResponseEntity<?> updateCustomerById(@PathVariable("customerid") Long customerId, @RequestBody Customer customer){
        return customerService.updateCustomerById(customerId, customer);
    }
    @DeleteMapping("/customers/{customerid}")
    @PreAuthorize("hasAnyAuthority('USER_FRO', 'ADMIN')")
    public ResponseEntity<?> deleteCustomerById(@PathVariable("customerid") Long customerId){
        return customerService.deleteCustomerById(customerId);
    }


}
