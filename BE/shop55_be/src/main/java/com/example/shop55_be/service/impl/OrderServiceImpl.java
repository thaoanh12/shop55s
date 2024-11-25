package com.example.shop55_be.service.impl;

import com.example.shop55_be.entity.Order;
import com.example.shop55_be.entity.OrderDetail;
import com.example.shop55_be.model.CustomOrder;
import com.example.shop55_be.reposistory.*;
import com.example.shop55_be.service.CustomerService;
import com.example.shop55_be.service.EmployeeService;
import com.example.shop55_be.service.OrderService;
import com.example.shop55_be.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private CustomOrderRepo customOrderRepo;
    @Override
    public Order save(Order order) {
        return orderRepo.save(order);
    }

    @Override
    public Order findOrderById(long id) {
        return orderRepo.findById(id).get();
    }

    @Override
    public Order findOrderByCode(String code) {
        return null;
    }

    @Override
    public CustomOrder findCustomOrderById(long id) {
        return customOrderRepo.getOrderById(id);
    }

    @Override
    public void deleteById(long id) {
          orderRepo.deleteById(id);
    }

    @Override
    public List<Order> getAll() {
        return orderRepo.getAll();
    }

    @Override
    public Page<Order> getAllOrderPaid(int number) {
        Pageable pageable = PageRequest.of(number,5);
        Page<Order> page = orderRepo.getAllOrderPaid(pageable);
        return page;
    }

    @Override
    public List<OrderDetail> getAllOrderDetail(Long id) {
        return orderRepo.findAllByOrderId(id);
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepo.findById(id);
    }
}
