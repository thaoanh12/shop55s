package com.example.shop55_be.service;

import com.example.shop55_be.entity.OrderDetail;
import com.example.shop55_be.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderDetailService {
    OrderDetail save(OrderDetail orderDetail);
    void deleteById(long id);
    List<OrderDetail> findOrderDetailByOrderId(long id);
    OrderDetail findOrderDetailById(long id);
    OrderDetail getOrderDetailByOrderIDAndProductDetailId(long orderId,String productDetailCode);



}
