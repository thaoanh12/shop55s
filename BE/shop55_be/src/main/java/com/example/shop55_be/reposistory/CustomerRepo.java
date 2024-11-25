package com.example.shop55_be.reposistory;

import com.example.shop55_be.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
    @Query(value = "SELECT * FROM customers",nativeQuery = true)
    Page<Customer> pagination(Pageable pageable);
    Optional<Customer> findByCustomerCode(String code);
    @Query("select customer from Customer customer " +
            "where customer.email like %:email% or " +
            "customer.fullName like  %:fullName% or" +
            " customer.customerCode like %:code%")
    Page<Customer> findCustomerByKeyWord( Pageable pageable, @Param("email")String email,
                                         @Param("fullName")String fullName,
                                         @Param("code")String code);
    @Query( value = "select * from customers where (customer_status = :status or :status is null) " , nativeQuery = true)
    Page<Customer> findCustomerBystatus(Pageable pageable , @Param("status") Long status);

    @Query(value = "select * from customers where phone_number = :phoneNumber ", nativeQuery = true )
    Customer findCustomerByPhone(@Param("phoneNumber") String phone);

    @Query(value = "select * from customers where customer_code = ?1",nativeQuery = true)
    Customer findByCusCode(String code);



}
