package com.example.shop55_be.homeController;

import com.example.shop55_be.entity.*;
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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@Controller
public class HomeController {
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
        model.addAttribute("nameCus", customerService.findBycode(code).getFullName());
        model.addAttribute("customerUser", customerService.findBycode(code));
        model.addAttribute("contentFragment", contentFragment);
        return "home/Home";
    }

    @GetMapping("/")
    public String home(Model model, @RequestParam("p") Optional<Integer> number) {
        Pageable pageable = PageRequest.of(number.orElse(0), 8);
        Page<Product> page = productService.getTu(pageable);
        Page<Product> page1 = productService.getProductsOrderByCreateDateDesc(pageable);
        model.addAttribute("page", page);
        model.addAttribute("page1", page1);
        model.addAttribute("contentFragment", "home/homePage.html");
        model.addAttribute("productShirt", categoryService.categorisShirt());
        model.addAttribute("productPants", categoryService.categorisPants());
        return "home/Home";
    }

    @GetMapping("/product/{id}")
    public String home(Model model, @PathVariable("id") Long id) {
        model.addAttribute("contentFragment", "home/productDetail.html");
        List<ProductDetail> list = productDetailService.findProductDetailByProductId(id);
        ProductDetail productDetail = list.get(0);
        model.addAttribute("img", productDetail.getThumbnail());
        model.addAttribute("productDetail", productDetail);
        model.addAttribute("colors", colorsService.getAll());
        model.addAttribute("sizes", sizeService.getAll());
        return "home/Home";
    }

    @GetMapping("/product-detail/{id}")
    public String productDetail(Model model, @RequestParam Long colorId,
                                @RequestParam Long sizeId, @PathVariable("id") Long id) {
        model.addAttribute("contentFragment", "home/productDetail.html");
        ProductDetail updatedProductDetail = productDetailService.findProductDetailBySizeAndAndColor(id, colorId, sizeId);
        model.addAttribute("img", updatedProductDetail.getThumbnail());
        model.addAttribute("productDetail", updatedProductDetail);
        model.addAttribute("colors", colorsService.getAll());
        model.addAttribute("sizes", sizeService.getAll());
        return "home/Home";
    }
    @PostMapping("/add-to-cart")
    public String addToCart (Model model,HttpServletRequest request,@RequestParam Long productDetailId,
                             @RequestParam int quantity,@RequestParam Long colorId,
                             @RequestParam Long sizeId,@RequestParam("productId") Long productId){
        String code = (String) request.getSession().getAttribute("code");
        Customer customer = customerService.findBycode(code);
        if (customer==null){
            return "Login";
        }
        model.addAttribute("contentFragment", "home/productDetail.html");
        ProductDetail updatedProductDetail = productDetailService.findProductDetailBySizeAndAndColor(productId, colorId, sizeId);
        model.addAttribute("img", updatedProductDetail.getThumbnail());
        model.addAttribute("productDetail", updatedProductDetail);
        model.addAttribute("colors", colorsService.getAll());
        model.addAttribute("sizes", sizeService.getAll());
        cartService.save(customer,productDetailId,quantity);
        return "home/Home";
    }

}
