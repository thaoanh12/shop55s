package com.example.shop55_be.reposistory;

import com.example.shop55_be.entity.OrderDetail;
import com.example.shop55_be.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderDetailRepo extends JpaRepository<OrderDetail,Long> {
    @Query(value = "SELECT * FROM order_detail where order_id= :id ",nativeQuery = true)
    List<OrderDetail> findOrderDetailByOrderId(Long id) ;

    @Query(value = "select order_detail.*\n" +
            "from order_detail join orders \n" +
            "on order_detail.order_id  = orders.id join product_detail \n" +
            "on order_detail.product_detail_id  = product_detail.id " +
            "where order_detail.order_id = :orderId and product_detail.product_detail_code = :productDetailCode",nativeQuery = true)
    OrderDetail getOrderDetailByOrderIdAndProductDetailCode(@Param("orderId")long orderId,
                                                           @Param("productDetailCode")String productDetailCode);

    @Query(value = "select * from order_detail where order_id = :orderId ", nativeQuery = true )
    OrderDetail findByOrderId(@Param("orderId") Long orderId);



}

