package com.example.shop55_be.service.impl;

import com.example.shop55_be.dto.EmployeeDto;
import com.example.shop55_be.dto.MailDto;
import com.example.shop55_be.entity.Employee;
import com.example.shop55_be.entity.Role;
import com.example.shop55_be.reposistory.EmployeeRepo;
import com.example.shop55_be.service.EmployeeService;
import com.example.shop55_be.service.MailService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private MailService mailService;
    @Override
    public boolean create(Employee employee) throws Exception  {
        String customCode = "NV" + new Random().nextInt(1000000);
        String password = RandomStringUtils.random(8,true,true);
        employee.setEmployeeCode(customCode);
        employee.setPasscode(encoder.encode(password));
        employee.setRole(Role.builder().id(4).build());
        employee.setNation("Việt Nam");
        employee.setCreateDate(new Date());
            employeeRepo.save(employee);
            MailDto mailDto = new MailDto();
            mailDto.setTo(employee.getEmail());
            mailDto.setSubject("Thông báo gửi mật khẩu");
            Map<String,Object> props = new HashMap<>();
            props.put("fullName",employee.getFullName());
            props.put("password",password);
            mailDto.setPropos(props);
            mailService.sendHtmlMail(mailDto,"Mail");
            return true;
    }

    @Override
    public boolean update(long id, Employee employee) {
        try {
            Employee updateEmployee = employeeRepo.findById(id).get();
            updateEmployee.setFullName(employee.getFullName());
            updateEmployee.setPhoneNumber(employee.getPhoneNumber());
            updateEmployee.setEmployeeAddress(employee.getEmployeeAddress());
            updateEmployee.setCommune(employee.getCommune());
            updateEmployee.setDistrict(employee.getDistrict());
            updateEmployee.setCity(employee.getCity());
            updateEmployee.setCreateDate(employee.getCreateDate());
            employeeRepo.save(updateEmployee);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean changePassword(long id, String newPassword) {
        try {
            Employee employee = employeeRepo.findById(id).get();
            employee.setPasscode(encoder.encode(newPassword));
            employeeRepo.save(employee);
            return true;
        }catch (Exception e){
            return false;
        }
    }


    @Override
    public List<EmployeeDto> getAll() {
        return employeeRepo.findAll().stream().
        map(EmployeeDto::new).collect(Collectors.toList());
    }

    @Override
    public Page<Employee> findEmployee(Pageable pageable) {
        return employeeRepo.findEmployee(pageable);
    }


    @Override
    public Employee findByMail(String mail) {
        return employeeRepo.findEmployeeByEmail(mail);
    }

    @Override
    public boolean layOff(long id) {
        try {
            Employee employee = employeeRepo.findById(id).get();
            employee.setEmployeeStatus(1);
            employeeRepo.save(employee);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Employee findById(long id) {
        return employeeRepo.findById(id).get();
    }

    @Override
    public Employee findByCode(String code) {
        return employeeRepo.findByEmployeeCode(code);
    }

    @Override
    public Page<Employee> findByKeyWord(Pageable pageable,String keyWord) {
        return employeeRepo.findEmployeeByKeyWord(pageable,keyWord,keyWord,keyWord,keyWord);
    }

    @Override
    public Page<Employee> findEmployeeByStatus(Pageable pageable, Integer status) {
        return employeeRepo.findEmployeeByStatus(pageable,status == 3 ? null : status);
    }
}
