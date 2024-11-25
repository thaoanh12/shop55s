package com.example.shop55_be.adminController;

import com.example.shop55_be.dto.ColorDto;
import com.example.shop55_be.entity.Colors;
import com.example.shop55_be.entity.Employee;
import com.example.shop55_be.service.ColorsService;
import com.example.shop55_be.service.EmployeeService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class ColorsController {
    @Autowired
    private ColorsService colorsService;
    @Autowired
    private EmployeeService employeeService;

    private String toAdminPages(Model model, HttpServletRequest request,
                                String contentFragment){
        String code = (String) request.getSession().getAttribute("code");
        model.addAttribute("contentFragment", contentFragment);
        model.addAttribute("employee",employeeService.findByCode(code));
        return "admin/adminPage";
    }

    @GetMapping("/color")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public String getAll(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p){
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Colors> colorsPage = colorsService.findColor(pageable);
        model.addAttribute("page",colorsPage);
        return toAdminPages(model,request,"/admin/product/colorPage");
    }

    @GetMapping("/color-seach")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public String seach(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p,
                        @RequestParam("keyword")String keyword){
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Colors> colorsPage = colorsService.findCustomerByKeyWords(pageable,keyword);
        model.addAttribute("page",colorsPage);
        model.addAttribute("url","/admin/color?keyword=" +keyword);
        return toAdminPages(model,request,"/admin/product/colorPage");
    }

    @GetMapping("/color-add")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public String colorViewAdd(Model model,
                                  HttpServletRequest request)throws Exception{

        model.addAttribute("colors",new Colors());
        return toAdminPages(model,request,"/admin/product/colorFormAdd");
    }

    @GetMapping("/view-update-color/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String detailCustomer(Model model, HttpServletRequest request, @PathVariable("id") Long id) {
        Colors colors = colorsService.DetailColor(id);
        model.addAttribute("colors", colors);
        return toAdminPages(model, request, "admin/product/colorUpdate");
    }


    @PostMapping("/color-add")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public String createColor (@Valid @ModelAttribute("colors") Colors colorsAdd, BindingResult result , Model model,HttpServletRequest request){
        Date date = new Date();

        if (result.hasErrors()) {
            return toAdminPages(model, request, "admin/product/colorFormAdd");
        }
        colorsAdd.setCreateDate(date);
        colorsAdd.setColorStatus(0);
        colorsService.saveColor(colorsAdd);
        model.addAttribute("success","Thành Công");
        return "redirect:/admin/color";
    }

    @PostMapping("/color-update")
    @PreAuthorize(" hasRole('ADMIN')")
    public String updateColor (@Valid @ModelAttribute("colors") Colors colorsAdd, BindingResult result , Model model,HttpServletRequest request){
        Date date = new Date();
        if (result.hasErrors()) {
            return toAdminPages(model, request, "admin/product/colorUpdate");
        }
        colorsAdd.setCreateDate(date);
        colorsAdd.setColorStatus(0);
        colorsService.saveColor(colorsAdd);
        model.addAttribute("success","Thành Công");
        return "redirect:/admin/color";
    }

    @GetMapping("/color-detail/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String employeeDetail(@PathVariable("id")long id,Model model,
                                 HttpServletRequest request
    ){
        model.addAttribute("colorDetail",colorsService.findById(id));
        return toAdminPages(model,request,"/admin/product/colorDetail");
    }
    @GetMapping("/status-color")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String fiter(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p
            , @RequestParam("status") Long status ) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Colors> colorsPage = colorsService.findColorBystatus(pageable,status);
        model.addAttribute("colors", colorsPage.getContent());
        model.addAttribute("page",colorsPage);
        model.addAttribute("url","/admin/color-seach?status="+status);
        return toAdminPages(model, request, "admin/product/colorPage");
    }
}
