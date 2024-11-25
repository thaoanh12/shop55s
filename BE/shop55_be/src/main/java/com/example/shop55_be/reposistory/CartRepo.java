package com.example.shop55_be.reposistory;

import com.example.shop55_be.entity.Cart;
import com.example.shop55_be.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepo extends JpaRepository<Cart,Long> {
    @Query(value = "select * from cart where customer_id = ?1",nativeQuery = true)
    List<Cart> findCartByCusId(Long id);

    @Query(value = "select * from cart where product_detail_id = ?1",nativeQuery = true)
    Cart findCartByProductDetailId(Long id);
    @Query("SELECT c FROM Cart c where c.productDetail.id = :id and c.customer = :cus")
    Cart findCartByProductDetailAndCustomer (@Param("id") Long id,@Param("cus") Customer cus);
}
