package com.example.shop55_be.adminController;

import com.example.shop55_be.dto.CustomerDto;
import com.example.shop55_be.entity.Colors;
import com.example.shop55_be.entity.Customer;
import com.example.shop55_be.entity.Employee;
import com.example.shop55_be.service.AddressService;
import com.example.shop55_be.service.CustomerService;
import com.example.shop55_be.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Controller
@RequestMapping("/admin")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private EmployeeService employeeService;

    private String toAdminPages(Model model, HttpServletRequest request,
                                String contentFragment) {
        String code = (String) request.getSession().getAttribute("code");
        model.addAttribute("contentFragment", contentFragment);
        model.addAttribute("employee", employeeService.findByCode(code));
        return "admin/adminPage";
    }

    @GetMapping("/customer")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String customerPage(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Customer> customersPage = customerService.getAlls(pageable);
        model.addAttribute("customers", customersPage.getContent());
        model.addAttribute("url", "/admin/customer");
        model.addAttribute("page", customersPage);
        return toAdminPages(model, request, "admin/customer/customerPage");
    }

    @GetMapping("/customer-view-add")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String addCustommer(Model model, HttpServletRequest request) {
        model.addAttribute("customerCreate", new Customer());
        return toAdminPages(model, request, "admin/customer/customerCreate");
    }

    @GetMapping("/customer-view-update/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String detailCustomer(Model model, HttpServletRequest request, @PathVariable("id") Long id) {
        model.addAttribute("customers", customerService.details(id));
        return toAdminPages(model, request, "admin/customer/customerUpdate");
    }

    @PostMapping("/customer-add")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String create(@Valid @ModelAttribute("customerCreate") Customer customer,BindingResult bindingResult,
                         Model model, HttpServletRequest request ) throws Exception {

        if (bindingResult.hasErrors()) {
            String code = (String) request.getSession().getAttribute("code");
            model.addAttribute("contentFragment", "admin/customer/customerCreate");
            model.addAttribute("employee", employeeService.findByCode(code));
            model.addAttribute("isError", true);
            model.addAttribute("customerCreate", customer);
            return "admin/adminPage";
        }
        customerService.customerCreate(customer);
        return "redirect:/admin/customer";
    }

    @PostMapping("/customer-update")
    public String update(@ModelAttribute("customers") Customer customer) {
        customerService.sustomerUpdate(customer);
        return "redirect:/admin/customer";
    }

    @GetMapping("/view-delete-customer/{id}")
    public String deleteCustomer(@PathVariable("id") Long id) {
        customerService.deletes(id);
        return "redirect:/admin/customer";
    }

    @GetMapping("/customer-search")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String search(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p
            , @RequestParam("kywword") String kyword) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Customer> customersPage = customerService.findCustomerByKeyWords(pageable, kyword);
        model.addAttribute("customers", customersPage.getContent());
        model.addAttribute("page", customersPage);
        model.addAttribute("url", "/admin/search-customer?kywword=" + kyword);
        return toAdminPages(model, request, "admin/customer/customerPage");
    }

    @GetMapping("/status-customer")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String fiter(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p
            , @RequestParam("status") Long status) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Customer> customersPage = customerService.findCustomerBystatus(pageable, status);
        model.addAttribute("customers", customersPage.getContent());
        model.addAttribute("page", customersPage);
        model.addAttribute("url", "/admin/search-customer?status=" + status);
        return toAdminPages(model, request, "admin/customer/customerPage");
    }


}
