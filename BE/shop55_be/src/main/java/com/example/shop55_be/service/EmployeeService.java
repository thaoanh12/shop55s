package com.example.shop55_be.service;

import com.example.shop55_be.dto.EmployeeDto;
import com.example.shop55_be.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface EmployeeService {
    boolean create(Employee employee) throws Exception;
    boolean update(long id,Employee employee);
    boolean changePassword(long id,String newPassword);
    List<EmployeeDto> getAll();
    Page<Employee> findEmployee(Pageable pageable);
    Employee findByMail(String mail);
    boolean layOff(long id);
    Employee findById(long id);
    Employee findByCode(String code);
    Page<Employee> findByKeyWord(Pageable pageable,String keyWord);
    Page<Employee> findEmployeeByStatus(Pageable pageable,Integer status);

}
