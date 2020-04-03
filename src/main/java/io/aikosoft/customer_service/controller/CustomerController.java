package io.aikosoft.customer_service.controller;

import io.aikosoft.customer_service.model.Customer;
import io.aikosoft.customer_service.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class CustomerController {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping(value = "/customers")
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerRepository.findAll();

        if (customers != null) {
            model.addAttribute("customers", customers);
        }

        return "customers";
    }

    @GetMapping(value = "/add")
    public String addCustomer() {
        return "add";
    }

    @GetMapping(value = "/update/{id}")
    public String updateCustomer(@PathVariable("id") Integer id, Model model) {
        Customer customer = customerRepository.findCustomerById(id);

        if (customer != null) {
            model.addAttribute("customer", customer);
        }

        return "update";
    }

    @PostMapping(value = "/add-customer")
    public String addCustomer(Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    @PostMapping(value = "/remove-customer")
    public String removeCustomer(Integer id) {
        customerRepository.deleteById(id);
        return "redirect:/customers";
    }

    @PostMapping(value = "/update-customer")
    public String updateCustomer(Customer customer) {
        boolean exists = customerRepository.existsById(customer.getId());
        if (exists) {
            customerRepository.deleteById(customer.getId());
            customerRepository.save(customer);
        }

        return "redirect:/customers";
    }
}
