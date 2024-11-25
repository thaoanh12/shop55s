package com.example.shop55_be.service;

import com.example.shop55_be.entity.Cart;
import com.example.shop55_be.entity.Customer;

import java.util.List;

public interface CartService {
    List<Cart> getCustomId(Long idCus);
    void UpdateQuantityCart(Long idProDetail,Integer quantity,Double totalAmountCart);
    void deleteCart(Long id);
    Cart save(Customer customer, Long productDetaiId, int quantity);

}
