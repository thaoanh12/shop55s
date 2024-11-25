package com.example.shop55_be.homeController;

import com.example.shop55_be.entity.Cart;
import com.example.shop55_be.entity.Customer;
import com.example.shop55_be.service.CartService;
import com.example.shop55_be.service.CustomerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.util.List;

@Controller

public class cartController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CartService cartService;


    private String toAdminPages(Model model, HttpServletRequest request,
                                String contentFragment) {
        String code = (String) request.getSession().getAttribute("code");
        model.addAttribute("nameCus",customerService.findBycode(code).getFullName());
        model.addAttribute("contentFragment", contentFragment);
        return "home/Home";
    }


    @GetMapping("/cart")
    @PreAuthorize(" hasRole('ROLE_CUSTOMER')")
    public String cart (Model model,HttpServletRequest request ){
        String code = (String) request.getSession().getAttribute("code");
        Customer customer = customerService.findBycode(code);
        Long idCus = customer.getId();
        List<Cart> lsCart = cartService.getCustomId(idCus);
        model.addAttribute("lsCart", lsCart);
        double totalPrice = 0;
        for (Cart cartItem : lsCart) {
            double price = cartItem.getQuantity() * cartItem.getProductDetail().getPrice();
            totalPrice += price;
        }

        model.addAttribute("totalAmount", totalPrice);
        return toAdminPages(model,request,"home/cart/cart");
    }

    @GetMapping("/updateGioHang/{id}/{quantity}/{totalAmount}")
    public String updateQuantityCart(@PathVariable("id") Long id, @PathVariable("quantity") Integer quantity,@PathVariable("totalAmount") Double totalAmountCart){
        cartService.UpdateQuantityCart(id,quantity,totalAmountCart);
        return "redirect:/cart";
    }

    @GetMapping("/deleteGioHang/{id}")
    public String removeCart(@PathVariable("id") Long id){
        cartService.deleteCart(id);
        return "redirect:/cart";

    }

}
