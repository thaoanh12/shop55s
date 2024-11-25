package com.example.shop55_be.reposistory;

import com.example.shop55_be.entity.Categories;
import com.example.shop55_be.entity.Fabrics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricsRepo extends JpaRepository<Fabrics , Long> {

    @Query(value = "SELECT f FROM Fabrics f")
    Page<Fabrics> findFabricsBy(Pageable pageable);

    @Query("select fabrics from Fabrics fabrics " +
            "where fabrics.fabricName like %:fabricName% ")
    Page<Fabrics> findFabricsByKeyWord(Pageable pageable,@Param("fabricName")String fabricName);

    @Query(value = "select * from fabrics where (fabric_status = :status or :status is null)", nativeQuery = true)
    Page<Fabrics> findfabricBystatus(Pageable pageable, @Param("status") Long status);
}
