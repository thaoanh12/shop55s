package com.example.shop55_be.reposistory;

import com.example.shop55_be.entity.Order;
import com.example.shop55_be.entity.OrderDetail;
import com.example.shop55_be.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order,Long> {
    @Query(value = "select * from orders where order_status = 0 order by create_date  desc ",nativeQuery = true)
    List<Order> getAll();
    @Query(value = "select * from orders order by create_date  desc ",nativeQuery = true)
    Page<Order> getAllOrderPaid(Pageable pageable);
    @Query("select od from OrderDetail od where od.order.id = :orderId")
    List<OrderDetail> findAllByOrderId(@Param("orderId") Long orderId);







}

