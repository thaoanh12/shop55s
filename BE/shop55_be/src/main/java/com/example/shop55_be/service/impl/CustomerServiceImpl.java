package com.example.shop55_be.service.impl;

import com.example.shop55_be.dto.CustomerDto;
import com.example.shop55_be.dto.MailDto;
import com.example.shop55_be.entity.Address;
import com.example.shop55_be.entity.Customer;
import com.example.shop55_be.entity.Role;
import com.example.shop55_be.reposistory.CustomerRepo;
import com.example.shop55_be.service.AddressService;
import com.example.shop55_be.service.CustomerService;
import com.example.shop55_be.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private AddressService addressService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private MailService mailService ;

    @Override
    public CustomerDto create(CustomerDto customerDto) throws Exception {

        String customerCode = "KH" + RandomStringUtils.random(8, true, true);
        CustomerDto result = new CustomerDto(customerRepo.save(Customer.builder()
                .customerCode(customerCode)
                .fullName(customerDto.getFullName())
                .email(customerDto.getEmail())
                .phoneNumber(customerDto.getPhoneNumber())
                .passcode(customerDto.getPasscode() == null ?
                        null : encoder.encode(customerDto.getPasscode())
                )
                .create_date(new Date())
                .avata(customerDto.getAvata())
                .build()));
        Customer customer =
                customerRepo.findByCustomerCode(customerCode).orElse(null);
        addressService.save(Address.builder()
                .customer(customer)
                .customerAddress(customerDto.getCustomerAddress())
                .commune(customerDto.getCommune())
                .district(customerDto.getDistrict())
                .city(customerDto.getCity())
                .nation("Việt Nam")
                .build());
        return result;
    }

    @Override
    public Page<CustomerDto> pagination(Pageable pageable) {
        Page<?> page = customerRepo.pagination(pageable);
        Page<CustomerDto> dtoPage = (Page<CustomerDto>) page;
        return dtoPage;
    }

    @Override
    public List<Customer> getAll() {
        return customerRepo.findAll();
    }

//    @Override
//    public List<CustomerDto> findCustomerByKeyWord(String keyword) {
//        return customerRepo.findCustomerByKeyWord(keyword, keyword, keyword)
//                .stream().map(CustomerDto::new).collect(Collectors.toList());
//    }

    @Override
    public Customer customerCreate(Customer customer)  throws Exception{
        String customCode = "NV" + new Random().nextInt(1000000);
        String password = RandomStringUtils.random(8,true,true);
        customer.setCustomerCode(customCode);
        customer.setPasscode(encoder.encode(password));
        customer.setCreate_date(new Date());
        customerRepo.save(customer);
        if(customer.getEmail() != null){
            MailDto mailDto = new MailDto();
            mailDto.setTo(customer.getEmail());
            mailDto.setSubject("Thông báo gửi mật khẩu");
            Map<String,Object> props = new HashMap<>();
            props.put("fullName",customer.getFullName());
            props.put("password",password);
            mailDto.setPropos(props);
            mailService.sendHtmlMail(mailDto,"Mail");
        }
        return customer;
    }

    @Override
    public Customer sustomerUpdate(Customer customer) {
        Customer customer1 = customerRepo.save(customer);
        return customer1;
    }

    @Override
    public Boolean deletes(Long id) {
        try {
            customerRepo.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Customer details(Long id) {
        return customerRepo.findById(id).get();
    }

    @Override
    public Page<Customer> getAlls(Pageable pageable) {
        return customerRepo.findAll(pageable);
    }

    @Override
    public Page<Customer> findCustomerByKeyWords(Pageable pageable, String keyword) {
        return customerRepo.findCustomerByKeyWord(pageable, keyword, keyword, keyword);
    }

    @Override
    public Page<Customer> findCustomerBystatus(Pageable pageable, Long status) {
        return customerRepo.findCustomerBystatus(pageable, status);
    }

    @Override
    public Customer findBycode(String code) {
        return customerRepo.findByCusCode(code);
    }


}
