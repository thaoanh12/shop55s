package com.example.shop55_be.adminController;

import com.example.shop55_be.entity.*;
import com.example.shop55_be.service.*;
import com.example.shop55_be.service.impl.AppService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ColorsService colorsService;
    @Autowired
    private SizeService sizeService;
    @Autowired
    private ProductService productService;
    @Autowired
    private AppService appService;

    private String toAdminPages(Model model, HttpServletRequest request,
                                String contentFragment) {
        String code = (String) request.getSession().getAttribute("code");
        model.addAttribute("contentFragment", contentFragment);
        model.addAttribute("employee", employeeService.findByCode(code));
        return "admin/adminPage";
    }

    @GetMapping("/product-detail")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String productPage(Model model, HttpServletRequest request) {
        model.addAttribute("productDetails", productDetailService.getAll());
        return toAdminPages(model, request, "admin/product/productPage");
    }

    @GetMapping("/productDetail/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String productDetail(@PathVariable("id") long id, Model model,
                                 HttpServletRequest request,
                                 @RequestParam(value = "keyword", required = false) String keyword,
                                 @RequestParam(value = "color", required = false) Long colorId,
                                 @RequestParam(value = "size", required = false) Long sizeId,
                                 @RequestParam(value = "p", required = false) Optional<Integer> number,
                                 @RequestParam(value = "sort", required = false) Optional<Integer> type
    ) {

        Sort sort;
        switch (type.orElse(0)) {
            case 1 -> {
                sort = Sort.by(Sort.Direction.DESC, "price");
            }
            case 2 -> {
                sort = Sort.by(Sort.Direction.ASC, "price");
            }
            default -> sort = Sort.unsorted();
        }
        Pageable pageable = PageRequest.of(number.orElse(0), 5, sort);
        Page<ProductDetail> page = productDetailService.sortAndFilter(pageable, colorId, sizeId, id, keyword, keyword);
        model.addAttribute("page", page);
        model.addAttribute("productId", id);
        model.addAttribute("colors", colorsService.getAll());
        model.addAttribute("sizes", sizeService.getAll());
        model.addAttribute("urlSortAndFileter", "/admin/productDetail/" + id + "?color=" +
                (colorId == null ? "" : colorId) + "&size=" + (sizeId == null ? "" : sizeId) + "&p=" + number.orElse(0) + "&keyword=" + keyword);
        model.addAttribute("url", "/admin/productDetail/" + id + "?color=" +
                (colorId == null ? "" : colorId) + "&size=" + (sizeId == null ? "" : sizeId) + "&sort=" + type.orElse(0) + "&keyword=" + keyword == null ? "" : keyword);
        return toAdminPages(model, request, "/admin/product/productDetail");
    }

    @GetMapping("/product-detail-view-add/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String productDetailViewAdd(Model model, @PathVariable("id") Long id,
                                       HttpServletRequest request) throws Exception {
        ProductDetail productDetail = new ProductDetail();
        Products product = productService.findById(id);
        productDetail.setProducts(product);
        model.addAttribute("newProductDetail", productDetail);
        List<Colors> colors = colorsService.getAll();
        List<Size> sizes = sizeService.getAll();
        model.addAttribute("product", product);
        model.addAttribute("colors", colors);
        model.addAttribute("sizes", sizes);
        return toAdminPages(model, request, "/admin/product/productDetailFormAdd");
    }

    @GetMapping("/product-detail-view-update/{id}")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String productDetailViewUpdate(Model model, @PathVariable("id") Long id,
                                          HttpServletRequest request) throws Exception {
        ProductDetail productDetail = productDetailService.findById(id);
        Products product = productService.findById(productDetail.getProducts().getId());
        productDetail.setProducts(product);
        model.addAttribute("newProductDetail", productDetail);
        List<Colors> colors = colorsService.getAll();
        List<Size> sizes = sizeService.getAll();
        model.addAttribute("product", product);
        model.addAttribute("colors", colors);
        model.addAttribute("sizes", sizes);
        return toAdminPages(model, request, "/admin/product/productDetailFormUpdate");
    }

    @PostMapping("/product-detail-add")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String add(@Valid @ModelAttribute("newProductDetail") ProductDetail productDetail, BindingResult bindingResult, Model model, @RequestParam("colorId")
            Long colorId, @RequestParam("sizeId") Long[] sizeId, @RequestParam("productId") Long productId, @RequestParam("image") MultipartFile file
            , HttpServletRequest request
    ) throws Exception {
        if (bindingResult.hasErrors()) {
            String code = (String) request.getSession().getAttribute("code");
            model.addAttribute("contentFragment", "admin/product/productDetailFormAdd");
            model.addAttribute("employee", employeeService.findByCode(code));
            model.addAttribute("isError", true);
            Products product = productService.findById(productId);
            productDetail.setProducts(product);
            model.addAttribute("newProductDetail", productDetail);
            model.addAttribute("product", product);
            List<Colors> colors = colorsService.getAll();
            List<Size> sizes = sizeService.getAll();
            model.addAttribute("colors", colors);
            model.addAttribute("sizes", sizes);
            return "admin/adminPage";
        }
        List<ProductDetail> productDetails = new ArrayList<>();
        for (Long ind : sizeId){
            ProductDetail productDetail1 = productDetailService.findProductDetailBySizeAndAndColor(productId,colorId,ind);
            if(productDetail1!=null){
                productDetails.add(productDetail1);
            }
        }
        if (!productDetails.isEmpty()){
            Products product = productService.findById(productId);
            productDetail.setProducts(product);
            String urlThumbnail = appService.upImageInClound(request, file);
            productDetail.setThumbnail(urlThumbnail);
            model.addAttribute("newProductDetail", productDetail);
            model.addAttribute("product", product);
            List<Colors> colors = colorsService.getAll();
            List<Size> sizes = sizeService.getAll();
            model.addAttribute("colors", colors);
            model.addAttribute("sizes", sizes);
            model.addAttribute("error","Sản phẩm đã tồn tại");
            return toAdminPages(model, request, "/admin/product/productDetailFormAdd");
        }
        String urlThumbnail = appService.upImageInClound(request, file);
        productDetail.setThumbnail(urlThumbnail);
        productDetailService.save(productDetail, colorId, sizeId, productId);
        return "redirect:/admin/product";
    }

    @PostMapping("/product-detail-update")
    public String update(@ModelAttribute("newProductDetail") ProductDetail productDetail, @RequestParam("colorId")
            Long colorId, @RequestParam("sizeId") Long[] sizeId, @RequestParam("productId") Long productId, @RequestParam("image") MultipartFile file
            , HttpServletRequest request
    ) throws Exception {
        if (file.getOriginalFilename().length() > 0) {
            String urlImage = appService.upImageInClound(request, file);
            productDetail.setThumbnail(urlImage);
        }
        productDetailService.save(productDetail, colorId, sizeId, productId);
        return "redirect:/admin/product";
    }


    @GetMapping("/order-product-detail")
    @PreAuthorize(" hasRole('ADMIN') or hasRole('STAFF')")
    public String getOrderProduct(Model model, HttpServletRequest request, @RequestParam("p") Optional<Integer> p) {
        Pageable pageable = PageRequest.of(p.orElse(0), 5);
        Page<ProductDetail> page = productDetailService.sortAndFilter(pageable,null,null,null,null,null);
        model.addAttribute("orderProduct", page.getContent());
        return toAdminPages(model, request, "/admin/sales/salesPage");
    }

}
