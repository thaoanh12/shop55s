package com.example.shop55_be.reposistory;

import com.example.shop55_be.model.Statistics;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;


public interface StatisticsRepo extends JpaRepository<Statistics,Integer>  {
    @Query(value = "select ifnull(sum(order_detail.total_amount),0) as 'tt'\n" +
            "from orders join order_detail on orders.id = order_detail.order_id \n" +
            "where Month(orders.pay_date) = :month and year(orders.pay_date) = :year",nativeQuery = true)
    double monthlyRevenueInYear(@Param("month")long month, @Param("year")long year);

    @Query(value = "select ifnull(sum(order_detail.total_amount),0) as 'daily_revenue' \n" +
            "from orders join order_detail on orders.id  = order_detail.order_id \n" +
            "where day(pay_date ) = day(now())and month(pay_date) = month(now()) and year(pay_date)=year(now())",nativeQuery = true)
    double dailyRevenue();

    @Query(value = "select ifnull(sum(order_detail.total_amount),0) as 'month_revenue' \n" +
            "from orders join order_detail on orders.id  = order_detail.order_id \n" +
            "where month(pay_date) = month(now()) and year(pay_date)=year(now())",nativeQuery = true)
    double monthlyRevenue();

    @Query(value = "select ifnull(sum(order_detail.total_amount),0) as 'year_revenue' \n" +
            "from orders join order_detail on orders.id  = order_detail.order_id \n" +
            "where year(pay_date)=year(now())",nativeQuery = true)
    double yearlyRevenue();

    @Query(value = "select count(id)  as 'daily_invoice_count' from orders\n" +
            "where day(pay_date ) = day(now())and month(pay_date) = month(now()) and year(pay_date)=year(now())",nativeQuery = true)
    int dailyInvoiceCount();

}
