package com.example.shop55_be.reposistory;

import com.example.shop55_be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<User, String> {
    @Query(value = "select * from (\n" +
            "select employee_code as 'code',full_name, email ,phone_number,passcode ,avata ,employee_status as 'status','employee' as 'type'\n" +
            "             from employees\n" +
            "            union\n" +
            "            select customer_code as 'code',full_name, email ,phone_number,passcode, avata,customer_status as 'status','customer' as 'type'\n" +
            "              from customers\n" +
            ")as merge_data\n" +
            "where status = 0", nativeQuery = true)
    List<User> getAllUser();

    @Query(value ="select * from (\n" +
            "select employee_code as 'code',full_name, email ,phone_number,passcode ,avata ,employee_status as 'status','employee' as 'type'\n" +
            "             from employees\n" +
            "            union\n" +
            "            select customer_code as 'code',full_name, email ,phone_number,passcode, avata,customer_status as 'status','customer' as 'type'\n" +
            "              from customers\n" +
            ")as merge_data\n" +
            "where  email = :email and status =0",nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);

    @Query(value ="select * from (\n" +
            "select employee_code as 'code',full_name, email ,phone_number,passcode ,avata ,employee_status as 'status','employee' as 'type'\n" +
            "             from employees\n" +
            "            union\n" +
            "            select customer_code as 'code',full_name, email ,phone_number,passcode, avata,customer_status as 'status','customer' as 'type'\n" +
            "              from customers\n" +
            ")as merge_data\n" +
            "where  code = :code and status =0",nativeQuery = true)
    Optional<User> findByCode(@Param("code") String code);

}
