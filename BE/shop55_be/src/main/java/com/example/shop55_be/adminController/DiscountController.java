package com.example.shop55_be.adminController;

import com.example.shop55_be.entity.Discount;
import com.example.shop55_be.entity.Products;
import com.example.shop55_be.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class DiscountController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private DiscountService discountService;





    private String toAdminPages(Model model, HttpServletRequest request,
                                String contentFragment) {
        String code = (String) request.getSession().getAttribute("code");
        model.addAttribute("contentFragment", contentFragment);
        model.addAttribute("employee", employeeService.findByCode(code));
        return "admin/adminPage";
    }

    @GetMapping("/discount")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String discountPage(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> number) {
        Pageable pageable = PageRequest.of(number.orElse(0), 5);
        Page<Discount> page = discountService.findDiscount(pageable);
        model.addAttribute("page", page);
        return toAdminPages(model, request, "admin/discount/discountPage");

    }

    @GetMapping("/discount-detail/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String discountDetail(Model model, HttpServletRequest request, @PathVariable("id") long id, @RequestParam("p") Optional<Integer> number) {

        model.addAttribute("discount", discountService.details(id));
        return toAdminPages(model, request, "admin/discount/discountDetail");
    }

    @GetMapping("/discount-view-add")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String discountAdd(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> number, @ModelAttribute("discount") Discount discount) {
        model.addAttribute("discount", new Discount());
        return toAdminPages(model, request, "admin/discount/discountAdd");
    }

    @PostMapping("/discount-creat")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String creatDiscount(@Valid @ModelAttribute("discount") Discount discount, BindingResult bindingResult,
                                Model model, HttpServletRequest request,@RequestParam("p") Optional<Integer> number
    ) throws Exception {
        if (bindingResult.hasErrors()) {
            String code = (String) request.getSession().getAttribute("code");
            model.addAttribute("contentFragment","admin/discount/discountAdd");
            model.addAttribute("employee",employeeService.findByCode(code));
            model.addAttribute("isError",true);
            model.addAttribute("discount",discount);
            return "admin/adminPage";
        }



        Double value = discount.getDiscountValue();

        if(value != null){
            Double doubleValue = value.doubleValue();
            System.out.println("Discount value: " + doubleValue);
        }else {
            String code = (String) request.getSession().getAttribute("code");
            model.addAttribute("contentFragment", "admin/discount/discountAdd");
            model.addAttribute("employee", employeeService.findByCode(code));
            model.addAttribute("discount", discount);
            model.addAttribute("abc", "Giá trị khuyen mai khong duoc bo trong");
            return "admin/adminPage";
        }

        if(discount.getDiscountType() == 1 && discount.getDiscountValue()>=50){

            String code = (String) request.getSession().getAttribute("code");
            model.addAttribute("contentFragment", "admin/discount/discountAdd");
            model.addAttribute("employee", employeeService.findByCode(code));
            model.addAttribute("discount", discount);
            model.addAttribute("errorMessage", "Giá trị khuyến mãi phần trăm phải nhỏ hơn 50");

            return "admin/adminPage";
        }
        if(discount.getMaximumDiscountAmount() >= discount.getDiscountValue()){

            String code = (String) request.getSession().getAttribute("code");
            model.addAttribute("contentFragment", "admin/discount/discountAdd");
            model.addAttribute("employee", employeeService.findByCode(code));
            model.addAttribute("discount", discount);
            model.addAttribute("errorMessages", "Tổng giá trị khuyến mãi phải lớn hơn hoặc bằng giá trị khuyến mãi");
        }

        Date date = new Date();
        if(discount.getCreateDate().before(date)){
            String code = (String) request.getSession().getAttribute("code");
            model.addAttribute("contentFragment", "admin/discount/discountAdd");
            model.addAttribute("employee", employeeService.findByCode(code));
            model.addAttribute("discount", discount);
            model.addAttribute("thongbao", "Ngày bắt đầu phải lớn hơn hoặc bằng ngày hiện tại");
            return "admin/adminPage";
        }
        discountService.addDiscount(discount);

        return "redirect:/admin/discount";
    }

    @GetMapping("/discount-status")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String fiter(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p
            , @RequestParam("status") Boolean status) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Discount> discountPage = discountService.findCustomerBystatus(pageable, status);
        model.addAttribute("discount", discountPage.getContent());
        model.addAttribute("page", discountPage);
        model.addAttribute("url", "/admin/discount-status?status=" + status);
        return toAdminPages(model, request, "admin/discount/discountPage");
    }

    @GetMapping("/discount-type")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String fitertype(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p
            , @RequestParam("type") Boolean type) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Discount> discountPage = discountService.findCustomerByType(pageable, type);
        model.addAttribute("discount", discountPage.getContent());
        model.addAttribute("page", discountPage);
        model.addAttribute("url", "/admin/discount-type?type=" + type);
        return toAdminPages(model, request, "admin/discount/discountPage");
    }

    @GetMapping("/search-discount")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String search(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p
            , @RequestParam("kywword") String kyword) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Discount> discountPage = discountService.findDiscountByKeyWords(pageable, kyword);
        model.addAttribute("discount", discountPage.getContent());
        model.addAttribute("page", discountPage);
        model.addAttribute("url", "/admin/discount-type?type=" + kyword);
        return toAdminPages(model, request, "admin/discount/discountPage");
    }


    @GetMapping("discount-view-update/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String updateDiscount(Model model,@PathVariable("id")Long id, HttpServletRequest request){
        model.addAttribute("discount", discountService.details(id));
        return toAdminPages(model, request, "admin/discount/discountUpdate");
    }

    @PostMapping("/discount-update")
    public String update(@ModelAttribute("discount") Discount discount){
        discountService.dicountUpdate(discount);
        return "redirect:/admin/discount";
    }

}