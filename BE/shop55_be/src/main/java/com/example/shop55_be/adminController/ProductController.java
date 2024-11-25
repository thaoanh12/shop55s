package com.example.shop55_be.adminController;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.shop55_be.entity.Categories;
import com.example.shop55_be.entity.Fabrics;
import com.example.shop55_be.entity.Products;
import com.example.shop55_be.service.*;
import com.example.shop55_be.service.impl.AppService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@Controller
@RequestMapping("/admin")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private FabricsService fabricsService;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private AppService appService;
    private String toAdminPages(Model model, HttpServletRequest request,
                                String contentFragment){
        String code = (String) request.getSession().getAttribute("code");
        model.addAttribute("contentFragment", contentFragment);
        model.addAttribute("employee",employeeService.findByCode(code));
        return "admin/adminPage";
    }
    @GetMapping("/product")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String productPage(Model model,HttpServletRequest request
            ,@RequestParam("p") Optional<Integer> number){
        Pageable pageable = PageRequest.of(number.orElse(0),5);
        Page<Products> page = productService.page(pageable);
        model.addAttribute("page",page);
        return toAdminPages(model,request,"admin/product/productPage");
    }
    @GetMapping("/product-search")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String productSearch(Model model,HttpServletRequest request
            ,@RequestParam("p") Optional<Integer> number,
            @RequestParam(value = "keyword",required = false)String keyword){
        Pageable pageable = PageRequest.of(number.orElse(0),5);
        Page<Products> page = productService.searchProduct(pageable,keyword,keyword);
        model.addAttribute("page",page);
        return toAdminPages(model,request,"admin/product/productPage");
    }
    @GetMapping("/product-detail/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String productDetail(@PathVariable("id")Long id,Model model,
                                 HttpServletRequest request
    ){
        model.addAttribute("productDetail",productDetailService.findById(id));
        return toAdminPages(model,request,"/admin/product/productDetailDetail");
    }

    @PostMapping("/product-add")
    public String add(@Valid @ModelAttribute("newProduct") Products product, BindingResult bindingResult, Model model, @RequestParam("categoryId")
            Long categoryId, @RequestParam("fabricId") Long fabricId, @RequestParam("image") MultipartFile file
            , HttpServletRequest request
    ) throws Exception {

        if(bindingResult.hasErrors()){
            String code = (String) request.getSession().getAttribute("code");
            model.addAttribute("contentFragment", "admin/product/productFormAdd");
            model.addAttribute("employee", employeeService.findByCode(code));
            model.addAttribute("isError", true);
            model.addAttribute("newProduct", product);
            List<Categories> categories = categoryService.getAll();
            List<Fabrics> fabrics = fabricsService.getAll();
            model.addAttribute("categoris",categories);
            model.addAttribute("fabrics",fabrics);
            return "admin/adminPage";
        }
        String urlImage = appService.upImageInClound(request,file);
        product.setThumbnail(urlImage);
        productService.save(product,categoryId,fabricId);
        return "redirect:/admin/product";
    }
    @PostMapping("/product-update")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String update(@Valid @ModelAttribute("newProduct") Products product, @RequestParam("categoryId")
            Long categoryId, @RequestParam("fabricId") Long fabricId,@RequestParam("image") MultipartFile file
            ,HttpServletRequest request
    ) throws Exception {
        if(file.getContentType().length()>0){
            String urlImage = appService.upImageInClound(request,file);
            product.setThumbnail(urlImage);
        }
        productService.save(product,categoryId,fabricId);
        return "redirect:/admin/product";
    }
    @GetMapping("/product-view-add")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String productViewAdd(Model model,
                                  HttpServletRequest request)throws Exception{
        model.addAttribute("newProduct",new Products());
        List<Categories> categories = categoryService.getAll();
        List<Fabrics> fabrics = fabricsService.getAll();
        model.addAttribute("categoris",categories);
        model.addAttribute("fabrics",fabrics);
        return toAdminPages(model,request,"/admin/product/productFormAdd");
    }
    @GetMapping("/product-view-update/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String productViewUpdate(Model model,@PathVariable("id") Long id,
                                 HttpServletRequest request)throws Exception{
        Products products = productService.findById(id);
        model.addAttribute("newProduct",products);
        List<Categories> categories = categoryService.getAll();
        List<Fabrics> fabrics = fabricsService.getAll();
        model.addAttribute("categoris",categories);
        model.addAttribute("fabrics",fabrics);
        return toAdminPages(model,request,"/admin/product/productFormUpdate");
    }
}
