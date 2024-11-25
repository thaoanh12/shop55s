package com.example.shop55_be.adminController;

import com.example.shop55_be.dto.CategoryDto;
import com.example.shop55_be.entity.Categories;
import com.example.shop55_be.entity.Colors;
import com.example.shop55_be.service.CategoryService;
import com.example.shop55_be.service.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
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
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")

public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private EmployeeService employeeService;

    private String toAdminPages(Model model, HttpServletRequest request,
                                String contentFragment) {
        String code = (String) request.getSession().getAttribute("code");
        model.addAttribute("contentFragment", contentFragment);
        model.addAttribute("employee", employeeService.findByCode(code));
        return "admin/adminPage";
    }


    @GetMapping("/category")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public String getAll(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Categories> cattegoriesPage = categoryService.findCategories(pageable);
        model.addAttribute("page", cattegoriesPage);
        return toAdminPages(model, request, "/admin/product/categoryPage");
    }

    @GetMapping("/category-seach")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public String seach(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p,
                        @RequestParam("keyword")String keyword){
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Categories> categoriesPagePage = categoryService.findCategoriesByKeyWords(pageable,keyword);
        model.addAttribute("page",categoriesPagePage);
        model.addAttribute("url","/admin/category?keyword=" +keyword);
        return toAdminPages(model,request,"/admin/product/categoryPage");
    }

    @GetMapping("/category-add")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public String colorViewAdd(Model model,
                               HttpServletRequest request) throws Exception {
        model.addAttribute("newCategory", new Categories());
        return toAdminPages(model, request, "/admin/product/categoryFormAdd");
    }

    @PostMapping("/category-add")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public String createCategory(@ModelAttribute("newCategory") @Valid Categories categoriesAdd, BindingResult result, Model model, HttpServletRequest request) {
        Date date = new Date();
        if (result.hasErrors()) {
            return toAdminPages(model, request, "admin/product/categoryFormAdd");
        }
        if (categoriesAdd.getId() == null) {
            categoriesAdd.setCreateDate(date);
            categoriesAdd.setCategoryStatus(0);
            categoryService.add(categoriesAdd);
            Categories categories = categoryService.detail(categoriesAdd.getId());
            categoriesAdd.setCreateDate(categories.getCreateDate());
            return "redirect:/admin/category";
        }
        categoryService.add(categoriesAdd);
        return "redirect:/admin/category";
    }

    @PostMapping("/category-update")
    @PreAuthorize("hasRole('ADMIN') or hasRole('STAFF')")
    public String UpdateCategory(@ModelAttribute("newCategory") @Valid Categories categoriesAdd, BindingResult result, Model model, HttpServletRequest request) {
        Date date = new Date();
        if (result.hasErrors()) {
            return toAdminPages(model, request, "admin/product/categoryUpdate");
        }
        if (categoriesAdd.getId() == null) {
            categoriesAdd.setCreateDate(date);
            categoriesAdd.setCategoryStatus(0);
            categoryService.add(categoriesAdd);
            Categories categories = categoryService.detail(categoriesAdd.getId());
            categoriesAdd.setCreateDate(categories.getCreateDate());
            return "redirect:/admin/category";
        }
        categoryService.add(categoriesAdd);
        return "redirect:/admin/category";
    }

    @GetMapping("/view-update-category/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String detailCategory(Model model, HttpServletRequest request, @PathVariable("id") Long id) {
        Categories categories = categoryService.detail(id);
        model.addAttribute("newCategory", categories);
        return toAdminPages(model, request, "admin/product/categoryUpdate");
    }

    @GetMapping("/status-category")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String fiter(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p
            , @RequestParam("status") Long status ) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<Categories> categoriesPage = categoryService.findCategorBystatus(pageable,status);
        model.addAttribute("category", categoriesPage.getContent());
        model.addAttribute("page",categoriesPage);
        model.addAttribute("url","/admin/category-seach?status="+status);
        return toAdminPages(model, request, "admin/product/categoryPage");
    }

}
