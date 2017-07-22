package com.luv2code.springdemo.controller;

import com.luv2code.springdemo.dao.CustomerDAO;
import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    // need to inject the dao

    @Autowired
    private CustomerService customerService;


    @GetMapping("/list")
    public String listCustomers(Model model){
        // get customer from the dao
        List<Customer> customers = customerService.getCustomers();

        // add the customers to the model
        model.addAttribute("customers", customers);

        return "list-customers";
    }

    @GetMapping("/showFormForAdd")
    public String showForForAdd(Model model){
        Customer customer = new Customer();
        model.addAttribute("customer", customer);

        return "customer-form";
    }


    @PostMapping("/saveCustomer")
    public String saveCustoner(@ModelAttribute ("customer ") Customer customer){

        customerService.saveCustomer(customer);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showForForUpdate(@RequestParam("customerId") int id, Model model){
        Customer customer = customerService.getCustomer(id);
        model.addAttribute("customer", customer);

        return "customer-form";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int id){
        customerService.deleteCustomer(id);

        return "redirect:/customer/list";
    }

}
