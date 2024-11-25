package com.example.shop55_be.service.impl;

import com.example.shop55_be.entity.Cart;
import com.example.shop55_be.entity.Customer;
import com.example.shop55_be.entity.ProductDetail;
import com.example.shop55_be.reposistory.CartRepo;
import com.example.shop55_be.reposistory.CustomerRepo;
import com.example.shop55_be.reposistory.ProductDetailRepo;
import com.example.shop55_be.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartRepo cartRepo;
    @Autowired
    ProductDetailRepo productDetailRepo;

    @Override
    public List<Cart> getCustomId(Long idCus) {
        return cartRepo.findCartByCusId(idCus);
    }

    @Override
    public void UpdateQuantityCart(Long idProDetail, Integer quantity, Double totalAmountCart) {
        Cart cartProdetail = cartRepo.findCartByProductDetailId(idProDetail);
        cartProdetail.setQuantity(quantity);
        cartProdetail.setTotalAmount(cartProdetail.getProductDetail().getPrice() * cartProdetail.getQuantity());
        cartRepo.save(cartProdetail);
    }

    @Override
    public void deleteCart(Long id) {
        Cart cart = cartRepo.findById(id).get();
        cartRepo.delete(cart);

    }

    @Override
    public Cart save(Customer customer, Long productDetaiId, int quantity) {
        ProductDetail productDetail = productDetailRepo.findById(productDetaiId).get();
        Cart cart = cartRepo.findCartByProductDetailAndCustomer(productDetaiId,customer);
        if (cart==null){
            cart = new Cart();
            cart.setCustomer(customer);
            cart.setProductDetail(productDetail);
            cart.setQuantity(quantity);
            cart.setCreateDate(new Date());
            cart.setTotalAmount(quantity*productDetail.getPrice());
        }
        else {
            cart.setQuantity(cart.getQuantity()+quantity);
        }
        return cartRepo.save(cart);
    }
}
