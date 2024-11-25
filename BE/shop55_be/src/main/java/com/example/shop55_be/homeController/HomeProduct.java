package com.example.shop55_be.homeController;

import com.example.shop55_be.entity.OrderDetail;
import com.example.shop55_be.entity.ProductDetail;
import com.example.shop55_be.entity.Products;
import com.example.shop55_be.model.Product;
import com.example.shop55_be.service.OrderDetailService;
import com.example.shop55_be.service.ProductDetailService;
import com.example.shop55_be.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import com.example.shop55_be.entity.ProductDetail;
import com.example.shop55_be.service.*;
import com.example.shop55_be.service.CartService;
import com.example.shop55_be.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Optional;
import java.util.List;

@Controller
public class HomeProduct {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private ColorsService colorsService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    CustomerService customerService;
    @Autowired
    CartService cartService;
    @Autowired
    private CategoryService categoryService ;

    private String toHomePages(Model model, HttpServletRequest request,
                               String contentFragment) {
        String code = (String) request.getSession().getAttribute("code");
        model.addAttribute("nameCus",customerService.findBycode(code).getFullName());
        model.addAttribute("customerUser",customerService.findBycode(code));
        model.addAttribute("contentFragment", contentFragment);
        return "home/Home";
    }

    @GetMapping("/product-all")
    public String productAll(Model model, @RequestParam("p") Optional<Integer> number) {
        model.addAttribute("contentFragment", "home/HomeProductsAll.html");
        Pageable pageable = PageRequest.of(number.orElse(0), 12);
        Page<Product> page = productService.homeProducAll(pageable);
        System.out.println(page.getContent());
        model.addAttribute("productShirt", categoryService.categorisShirt());
        model.addAttribute("productPants", categoryService.categorisPants());
        model.addAttribute("url", "/product-all");
        model.addAttribute("productHome", page);
        model.addAttribute("productHomeSelLing", productService.bestSellingProduct());
        return "home/Home";
    }
    @GetMapping("/product-mens-shirt/{productName}")
    public String productMensShirt(Model model, @RequestParam("p") Optional<Integer> number
            , @PathVariable("productName") String productName) {
        model.addAttribute("contentFragment", "home/HomeProductMensShirt.html");
        Pageable pageable = PageRequest.of(number.orElse(0), 12);
        Page<Product> page = productService.productMensShirt(pageable,productName);
        System.out.println(page.getContent());
        model.addAttribute("productShirt", categoryService.categorisShirt());
        model.addAttribute("productPants", categoryService.categorisPants());
        model.addAttribute("url", "/product-all");
        model.addAttribute("productMensShirt", page);
        model.addAttribute("productHomeSelLing", productService.bestSellingProduct());
        return "home/Home";
    }
    @GetMapping("/product-mens-pants/{productName}")
    public String productMensPants(Model model, @RequestParam("p") Optional<Integer> number
            , @PathVariable("productName") String productName) {
        model.addAttribute("contentFragment", "home/HomeProductMensPants.html");
        Pageable pageable = PageRequest.of(number.orElse(0), 12);
        Page<Product> page = productService.productMensShirt(pageable, productName);
        System.out.println(page.getContent());
        model.addAttribute("productShirt", categoryService.categorisShirt());
        model.addAttribute("productPants", categoryService.categorisPants());
        model.addAttribute("url", "/product-all");
        model.addAttribute("productMensPants", page);
        model.addAttribute("productHomeSelLing", productService.bestSellingProduct());
        return "home/Home";
    }
    @GetMapping("/product-filter-ascending")
    public String getProductsHomeFilterAscending(Model model, @RequestParam("p") Optional<Integer> number) {
        model.addAttribute("contentFragment", "home/HomeProductsAll.html");
        Pageable pageable = PageRequest.of(number.orElse(0), 12);
        Page<Product> page = productService.getProductsHomeFilterAscending(pageable);
        System.out.println(page.getContent());
        model.addAttribute("productShirt", categoryService.categorisShirt());
        model.addAttribute("productPants", categoryService.categorisPants());
        model.addAttribute("url", "/product-filter-ascending");
        model.addAttribute("productHome", page);
        model.addAttribute("productHomeSelLing", productService.bestSellingProduct());
        return "home/Home";
    }
    @GetMapping("/product-filter-decrease")
    public String getProductsHomeFilterDecrease(Model model, @RequestParam("p") Optional<Integer> number) {
        model.addAttribute("contentFragment", "home/HomeProductsAll.html");
        Pageable pageable = PageRequest.of(number.orElse(0), 12);
        Page<Product> page = productService.getProductsHomeFilterDecrease(pageable);
        System.out.println(page.getContent());
        model.addAttribute("productShirt", categoryService.categorisShirt());
        model.addAttribute("productPants", categoryService.categorisPants());
        model.addAttribute("url", "/product-filter-ascending");
        model.addAttribute("productHome", page);
        model.addAttribute("productHomeSelLing", productService.bestSellingProduct());
        return "home/Home";
    }
    @GetMapping("/product-mens-filter-shirt")
    public String getProductsHomeFilterAscendingShirt(Model model, @RequestParam("p") Optional<Integer> number) {
        model.addAttribute("contentFragment", "home/HomeProductMensShirt.html");
        Pageable pageable = PageRequest.of(number.orElse(0), 12);
        Page<Product> page = productService.getProductsHomeFilterAscendingShirt(pageable);
        System.out.println(page.getContent());
        model.addAttribute("productShirt", categoryService.categorisShirt());
        model.addAttribute("productPants", categoryService.categorisPants());
        model.addAttribute("url", "/product-all");
        model.addAttribute("productMensShirt", page);
        model.addAttribute("productHomeSelLing", productService.bestSellingProduct());
        return "home/Home";
    }

}
