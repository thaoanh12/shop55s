package com.example.shop55_be.reposistory;

import com.example.shop55_be.model.CustomOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomOrderRepo extends JpaRepository<CustomOrder,Long> {
    @Query(value = "select o.*,ifnull(sum(od.total_amount),0)as 'total_amount'  from orders o left join order_detail od on o.id  = od.order_id\n" +
            "            where o.id  = :id\n" +
            "            group by o.id",nativeQuery = true)
    CustomOrder getOrderById(@Param("id")long id);
}
