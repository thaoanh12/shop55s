package com.example.shop55_be.reposistory;

import com.example.shop55_be.entity.Customer;
import com.example.shop55_be.entity.Discount;
import com.example.shop55_be.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

public interface DiscountRepo extends JpaRepository<Discount,Long> {





    @Query("SELECT discounts FROM Discount discounts " +
            "WHERE discounts.discountCode LIKE CONCAT('%', :keyword, '%') OR " +
            "discounts.note LIKE CONCAT('%', :keyword, '%') OR " +
            "(CAST(discounts.maximumDiscountAmount AS string) LIKE CONCAT('%', :keyword, '%') AND " +
            "discounts.discountValue BETWEEN :minValue AND :maxValue)"
    )
    Page<Discount> findDiscountByKeyWord(Pageable pageable,
                                         @Param("keyword") String keyword,
                                         @Param("minValue") Double minValue,
                                         @Param("maxValue") Double maxValue
    );



    @Query(value = "select * from discounts",nativeQuery = true)
    Page<Discount> findDiscount(Pageable pageable);


    Discount findDiscountByDiscountCode(String code);


    @Query(value = "SELECT * FROM discounts WHERE discount_status = :status OR :status IS NULL", nativeQuery = true)
    Page<Discount> findDiscountByStatus(Pageable pageable, @Param("status") Boolean status);

    @Query(value = "SELECT * FROM discounts WHERE discount_type = :type OR :type IS NULL", nativeQuery = true)
    Page<Discount> findDiscountByType(Pageable pageable, @Param("type") Boolean type);










}