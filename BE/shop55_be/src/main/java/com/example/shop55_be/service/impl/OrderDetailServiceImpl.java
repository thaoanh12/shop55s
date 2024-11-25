package com.example.shop55_be.service.impl;

import com.example.shop55_be.entity.OrderDetail;
import com.example.shop55_be.entity.Products;
import com.example.shop55_be.reposistory.*;
import com.example.shop55_be.service.CustomerService;
import com.example.shop55_be.service.EmployeeService;
import com.example.shop55_be.service.OrderDetailService;
import com.example.shop55_be.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductDetailRepo productDetailRepo;
    @Autowired
    private OrderDetailRepo orderDetailRepo;
    @Autowired
    private ProductDetailService productDetailService;
    @Autowired
    private CustomOrderRepo customOrderRepo;
    @Autowired
    private CustomerService customerService;

    @Override
    public OrderDetail save(OrderDetail orderDetail) {
        return orderDetailRepo.save(orderDetail);
    }

    @Override
    public void deleteById(long id) {
        orderDetailRepo.deleteById(id);
    }

    @Override
    public List<OrderDetail> findOrderDetailByOrderId(long id) {
        return orderDetailRepo.findOrderDetailByOrderId(id);
    }

    @Override
    public OrderDetail findOrderDetailById(long id) {
        return orderDetailRepo.findById(id).get();
    }



    @Override
    public OrderDetail getOrderDetailByOrderIDAndProductDetailId(long orderId, String productDetailCode) {
        return orderDetailRepo.getOrderDetailByOrderIdAndProductDetailCode(orderId,productDetailCode);
    }




}
