package com.example.shop55_be.service;

import com.example.shop55_be.entity.Order;
import com.example.shop55_be.entity.OrderDetail;
import com.example.shop55_be.model.CustomOrder;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order save(Order order);
    Order findOrderById(long id);
    Order findOrderByCode(String code);
    CustomOrder findCustomOrderById(long id);
    void deleteById(long id);
    List<Order> getAll();
    Page<Order> getAllOrderPaid(int number);
    List<OrderDetail> getAllOrderDetail(Long id);
    Optional<Order> getOrderById(Long id);
}
