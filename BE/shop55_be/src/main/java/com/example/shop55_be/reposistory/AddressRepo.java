package com.example.shop55_be.reposistory;

import com.example.shop55_be.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address,Long> {
}
